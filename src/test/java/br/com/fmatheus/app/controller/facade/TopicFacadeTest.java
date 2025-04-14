package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.TopicConverter;
import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.model.entity.Topic;
import br.com.fmatheus.app.model.service.TopicService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Testes unitários da classe TopicFacade")
@ContextConfiguration(classes = {TopicFacade.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class TopicFacadeTest {

    @Mock
    private TopicService topicService;

    @Mock
    private TopicConverter topicConverter;

    @InjectMocks
    private TopicFacade topicFacade;

    private TopicRequest topicRequest;

    private Topic topic;

    private Topic savedTopic;

    private TopicResponse topicResponse;

    @BeforeEach
    void setUp() {
        this.loadObjects();
    }

    @Test
    @Order(1)
    @DisplayName("Criar uma nova pauta com sucesso.")
    void testCreateTopicSuccessfully() {

        when(this.topicConverter.converterToEntity(this.topicRequest)).thenReturn(this.topic);
        when(this.topicService.save(this.topic)).thenReturn(this.savedTopic);
        when(this.topicConverter.converterToResponse(this.savedTopic)).thenReturn(this.topicResponse);

        TopicResponse result = this.topicFacade.create(this.topicRequest);

        assertNotNull(result);
        assertEquals(this.topicResponse.id(), result.id());
        assertEquals(this.topicResponse.title(), result.title());
        assertEquals(this.topicResponse.description(), result.description());
        assertEquals(this.topicResponse.createdAt(), result.createdAt());

        verify(this.topicConverter).converterToEntity(this.topicRequest);
        verify(this.topicService).save(this.topic);
        verify(this.topicConverter).converterToResponse(this.savedTopic);
    }

    void loadObjects() {
        this.topicRequest = new TopicRequest("Título da pauta", "Descrição da pauta");

        var date = LocalDateTime.now();
        var title = "Título da pauta";
        var description = "Descrição da pauta";

        this.topic = Topic.builder()
                .title(title)
                .description(description)
                .createdAt(date)
                .build();

        this.savedTopic = Topic.builder()
                .title(title)
                .description(description)
                .createdAt(date)
                .build();

        this.topicResponse = new TopicResponse(
                this.savedTopic.getId(),
                this.savedTopic.getTitle(),
                this.savedTopic.getDescription(),
                this.savedTopic.getCreatedAt()
        );
    }


}
