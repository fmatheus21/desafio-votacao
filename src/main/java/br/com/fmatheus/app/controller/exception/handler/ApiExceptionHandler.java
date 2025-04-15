package br.com.fmatheus.app.controller.exception.handler;

import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";
    private final MessageSource messageSource;

    /**
     * Trata erros de requisição com corpo malformado (ex: JSON inválido).
     *
     * @param ex      a exceção lançada durante o parsing da requisição
     * @param headers cabeçalhos HTTP
     * @param status  status HTTP
     * @param request a requisição atual
     * @return resposta HTTP 400 com detalhes do erro
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @Override
    protected @NonNull ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "O corpo da requisição está inválido ou mal formatado.");
        detail.setProperty(MESSAGE, ex.getMostSpecificCause().getMessage());
        return ResponseEntity.badRequest().body(detail);
    }

    /**
     * Trata erros de validação de argumentos (Bean Validation).
     *
     * @param ex      exceção de validação contendo os campos inválidos
     * @param headers cabeçalhos HTTP
     * @param status  status HTTP
     * @param request a requisição atual
     * @return resposta HTTP 400 com lista de campos inválidos
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        var errors = ex.getFieldErrors().stream()
                .map(e -> String.format("Campo '%s': %s", e.getField(), e.getDefaultMessage()))
                .toList();

        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Erro de validação nos campos fornecidos.");
        detail.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(detail);
    }

    /**
     * <p>Trata erros de incompatibilidade de tipo em parâmetros da requisição.</p>
     * <p>Exemplo: enviar um texto onde se espera um número.</p>
     *
     * @param ex exceção de incompatibilidade de tipo
     * @return resposta HTTP 400 com detalhes sobre o parâmetro inválido
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Tipo de argumento inválido.");
        detail.setProperties(Map.of(
                "parameter", ex.getName(),
                "expectedType", Optional.of(ex.getRequiredType())
                        .map(Class::getSimpleName)
                        .orElse("desconhecido")
        ));
        return ResponseEntity.badRequest().body(detail);
    }

    /**
     * Tratamento genérico para exceções não previstas.
     *
     * @param ex exceção lançada
     * @return resposta HTTP 500 com mensagem de erro
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneric(Exception ex) {
        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado.");
        detail.setProperty(MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detail);
    }

    /**
     * Trata exceções do tipo {@link BadRequestException}.
     *
     * @param ex exceção personalizada indicando erro de requisição
     * @return resposta HTTP 400 com mensagem de erro
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequest(Exception ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Trata exceções do tipo {@link NotFoundException}.
     *
     * @param ex exceção personalizada indicando recurso não encontrado
     * @return resposta HTTP 404 com mensagem de erro
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(Exception ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    /**
     * Constrói uma resposta de erro padrão no formato {@link ProblemDetail}.
     *
     * @param status status HTTP da resposta
     * @param ex     exceção lançada
     * @return {@link ResponseEntity} contendo o {@link ProblemDetail}
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private ResponseEntity<ProblemDetail> buildErrorResponse(HttpStatus status, Exception ex) {
        var detail = ProblemDetail.forStatusAndDetail(status, "Ocorreu um erro inesperado.");
        detail.setProperty(MESSAGE, resolveMessage(ex));
        return ResponseEntity.status(status).body(detail);
    }

    /**
     * Resolve a mensagem de erro a partir do código na exceção, consultando o {@link MessageSource}.
     *
     * @param ex exceção com a mensagem/código
     * @return mensagem localizada, ou a mensagem bruta da exceção caso não seja encontrada
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private String resolveMessage(Exception ex) {
        String code = Optional.ofNullable(ex.getMessage()).orElse("message.error.unknown");
        try {
            return messageSource.getMessage(code, new Object[0], LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return ex.getMessage();
        }
    }
}
