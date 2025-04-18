package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.SessionConverter;
import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.NotFoundException;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;
import br.com.fmatheus.app.model.service.SessionService;
import br.com.fmatheus.app.model.service.TopicService;
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

@DisplayName("Testes unitários da classe SessionFacade")
@ContextConfiguration(classes = {SessionFacade.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class SessionFacadeTest {

    private static final UUID ID_TOPIC = UUID.fromString("8853624b-ba5e-474a-9f22-c06fde7d1422");

    @Mock
    private SessionService sessionService;

    @Mock
    private TopicService topicService;

    @Mock
    private SessionConverter sessionConverter;

    @Mock
    private MessagesFacade messagesFacade;

    @InjectMocks
    private SessionFacade sessionFacade;

    private Topic topic;

    private Session session;

    private SessionResponse sessionResponse;

    private SessionRequest sessionRequest;

    @BeforeEach
    void setUp() {
        this.loadObjects();
    }

    @Test
    @Order(1)
    @DisplayName("Salva registro com sucesso.")
    void testCreateSuccess() {

        when(this.topicService.findById(ID_TOPIC)).thenReturn(Optional.of(this.topic));
        when(this.sessionService.findByTopic(any(Topic.class))).thenReturn(Optional.empty());
        when(this.sessionConverter.converterToEntity(this.sessionRequest, this.topic)).thenReturn(this.session);
        when(this.sessionService.save(this.session)).thenReturn(this.session);
        when(this.sessionConverter.converterToResponse(this.session)).thenReturn(this.sessionResponse);

        final SessionResponse response = this.sessionFacade.create(this.sessionRequest);

        assertNotNull(response);
        assertEquals(this.sessionResponse.id(), response.id());

        verify(this.topicService).findById(ID_TOPIC);
        verify(this.sessionService).findByTopic(any(Topic.class));
        verify(this.sessionConverter).converterToEntity(this.sessionRequest, this.topic);
        verify(this.sessionService).save(this.session);
    }

    @Test
    @Order(2)
    @DisplayName("Exceção lançada. A pauta já está vinculada a uma sessão.")
    void testCreateTopicAlreadySessionException() {
        UUID topicId = UUID.randomUUID();
        SessionRequest request = new SessionRequest(topicId);

        this.topic = Topic.builder().id(topicId).build();
        Session existingSession = Session.builder().id(UUID.randomUUID()).build();

        when(this.topicService.findById(topicId)).thenReturn(Optional.of(this.topic));
        when(this.sessionService.findByTopic(any(Topic.class))).thenReturn(Optional.of(existingSession));

        when(messagesFacade.errorTopicAlreadySession()).thenThrow(new BadRequestException("message.error.topic-exist-session"));
        var exception = assertThrows(BadRequestException.class, () -> sessionFacade.create(request));

        assertEquals("message.error.topic-exist-session", exception.getMessage());
        verify(this.topicService, times(1)).findById(topicId);
        verify(this.sessionService, times(1)).findByTopic(any(Topic.class));
        verify(this.sessionConverter, never()).converterToEntity(any(), any());
        verify(this.sessionService, never()).save(any());
    }

    @Test
    @Order(3)
    @DisplayName("Exceção lançada. A pauta não foi encontrada.")
    void testCreateTopicNotFoundException() {

        UUID idTopic = UUID.randomUUID();
        SessionRequest request = new SessionRequest(idTopic);

        when(this.topicService.findById(idTopic)).thenReturn(Optional.empty());

        when(messagesFacade.errorTopicNotFoundException()).thenThrow(new NotFoundException("message.error.topic-not-found"));
        var exception = assertThrows(NotFoundException.class, () -> sessionFacade.create(request));

        assertEquals("message.error.topic-not-found", exception.getMessage());
        verify(this.topicService, times(1)).findById(idTopic);
        verify(this.sessionConverter, never()).converterToEntity(any(SessionRequest.class), any(Topic.class));
        verify(this.sessionConverter, never()).converterToResponse(any(Session.class));
        verify(this.sessionService, never()).save(any(Session.class));
    }

    void loadObjects() {
        this.topic = Topic.builder()
                .id(ID_TOPIC)
                .title("Pauta Teste")
                .description("Descrição Teste")
                .build();

        var date = LocalDateTime.now();
        this.session = Session.builder()
                .id(UUID.fromString("ace4e198-d14e-4e9d-b99f-29db243cae90"))
                .start(date)
                .end(date.plusMinutes(1))
                .topic(this.topic)
                .build();

        this.sessionResponse = new SessionResponse(
                this.session.getId(),
                this.session.getStart(),
                this.session.getEnd(),
                new SessionResponse.TopicDto(this.topic.getId(), this.topic.getTitle(), this.topic.getDescription())
        );

        this.sessionRequest = new SessionRequest(ID_TOPIC);
    }


}

