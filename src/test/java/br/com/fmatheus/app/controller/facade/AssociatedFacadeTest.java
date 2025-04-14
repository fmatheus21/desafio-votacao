package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.AssociatedConverter;
import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.service.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes unitários da classe AssociatedFacade")
@ContextConfiguration(classes = {AssociatedFacade.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AssociatedFacadeTest {

    @Mock
    private PersonService personService;

    @Mock
    private AssociatedConverter associatedConverter;

    @Mock
    private MessagesFacade messagesFacade;

    @InjectMocks
    private AssociatedFacade associatedFacade;

    private AssociatedRequest associatedRequest;

    private AssociatedResponse associatedResponse;

    private Person person;

    @BeforeEach
    void setUp() {
        this.loadObjects();
    }

    @Test
    @Order(1)
    @DisplayName("Cadastra associado com sucesso.")
    void testCreateSuccess() {
        when(this.personService.findByDocument("12345678900")).thenReturn(Optional.empty());
        when(this.associatedConverter.converterToEntity(this.associatedRequest)).thenReturn(this.person);
        when(this.personService.save(this.person)).thenReturn(this.person);
        when(this.associatedConverter.converterToResponse(this.person)).thenReturn(this.associatedResponse);

        var result = this.associatedFacade.create(this.associatedRequest);

        assertNotNull(result);
        assertEquals("Pedro Nunes", result.name());
        verify(this.personService).findByDocument("12345678900");
        verify(this.personService).save(this.person);

    }

    @Test
    @Order(2)
    @DisplayName("Exceção lançada. O Documento já está cadastrado.")
    void testCreateException() {

        when(this.personService.findByDocument("12345678900")).thenReturn(Optional.of(this.person));

        when(messagesFacade.errorDocumentAlready()).thenThrow(new BadRequestException("message.error.exist-document"));
        var exception = assertThrows(BadRequestException.class, () -> this.associatedFacade.create(this.associatedRequest));

        assertEquals("message.error.exist-document", exception.getMessage());
        verify(this.personService, never()).save(any());
    }


    void loadObjects() {
        this.person = Person.builder()
                .name("Pedro Nunes")
                .document("12345678900")
                .build();

        var associated = Associated.builder()
                .id(UUID.fromString("674a3782-17bb-11f0-9044-581122c7752d"))
                .createdAt(LocalDateTime.now())
                .person(this.person)
                .build();

        this.person.setAssociated(associated);

        this.associatedResponse = new AssociatedResponse(
                associated.getId(),
                this.person.getName(),
                this.person.getDocument(),
                associated.getCreatedAt()
        );

        this.associatedRequest = new AssociatedRequest("Pedro Nunes", "12345678900");
    }


}

