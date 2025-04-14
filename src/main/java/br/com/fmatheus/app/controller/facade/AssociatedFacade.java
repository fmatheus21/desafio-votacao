package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.AssociatedConverter;
import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.model.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Facade responsável por encapsular a lógica de criação de associados (pessoas).
 * <p>
 * Este componente integra diferentes camadas da aplicação, coordenando validações,
 * conversões e persistência de dados relacionados à entidade "Person".
 * </p>
 *
 * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AssociatedFacade {

    private final PersonService personService;
    private final AssociatedConverter associatedConverter;
    private final MessagesFacade messagesFacade;

    /**
     * Cria um novo associado com base na requisição fornecida.
     * <p>
     * Antes de persistir os dados, é realizada uma verificação se o documento já está
     * cadastrado na base. Em caso positivo, uma exceção controlada é lançada via {@code messagesFacade}.
     * </p>
     *
     * @param request objeto {@link AssociatedRequest} com os dados para criação do associado.
     * @return um {@link AssociatedResponse} contendo os dados da entidade recém-criada.
     * @throws RuntimeException caso o documento já esteja cadastrado.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public AssociatedResponse create(AssociatedRequest request) {
        this.checksIfDocumentExists(request.document());

        var entity = this.associatedConverter.converterToEntity(request);
        var commit = this.personService.save(entity);
        return this.associatedConverter.converterToResponse(commit);

    }

    /**
     * Verifica se o documento informado já está cadastrado.
     * <p>
     * Caso o documento já exista, é disparada uma exceção de negócio através do {@link MessagesFacade}.
     * </p>
     *
     * @param document o documento a ser verificado (CPF ou CNPJ).
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private void checksIfDocumentExists(String document) {
        log.info("Verificando se o associado com documento {} está cadastrado.", document);
        var person = this.personService.findByDocument(document);
        if (person.isPresent()) {
            this.messagesFacade.errorDocumentAlready();
        }
    }

}
