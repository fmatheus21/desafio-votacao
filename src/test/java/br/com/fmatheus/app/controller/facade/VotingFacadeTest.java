package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.VotingConverter;
import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.controller.enums.VoteEnum;
import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.NotFoundException;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;
import br.com.fmatheus.app.model.service.AssociatedService;
import br.com.fmatheus.app.model.service.SessionService;
import br.com.fmatheus.app.model.service.VotingService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes unitários da classe VotingFacade")
@ContextConfiguration(classes = {VotingFacade.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class VotingFacadeTest {

    private static final UUID ID_SESSION = UUID.fromString("c5735b94-653a-4407-bf0d-2b17d938451a");
    private static final UUID ID_ASSOCIATED = UUID.fromString("fde5e64c-8eb3-417c-af31-70e1238dd730");

    @InjectMocks
    private VotingFacade votingFacade;

    @Mock
    private VotingService votingService;

    @Mock
    private SessionService sessionService;

    @Mock
    private AssociatedService associatedService;

    @Mock
    private VotingConverter votingConverter;

    @Mock
    private MessagesFacade messagesFacade;

    private VotingRequest request;

    private Associated associated;

    private Session session;

    private Voting voting;

    private VotingResponse response;

    @BeforeEach
    void setUp() {
        this.loadObjects();
    }

    @Test
    @Order(1)
    @DisplayName("Criar um novo voto com sucesso.")
    void testCreateVoteSuccess() {
        when(this.associatedService.findById(ID_ASSOCIATED)).thenReturn(Optional.of(this.associated));
        when(this.sessionService.findById(ID_SESSION)).thenReturn(Optional.of(this.session));
        when(this.sessionService.isCurrentTimeWithinSession(ID_SESSION)).thenReturn(true);
        when(this.votingService.findById(any())).thenReturn(Optional.empty());
        when(this.votingConverter.converterToEntity(any(), eq(this.associated), eq(this.session))).thenReturn(this.voting);
        when(this.votingService.save(any())).thenReturn(this.voting);
        when(this.votingConverter.converterToResponse(any())).thenReturn(this.response);

        VotingResponse result = this.votingFacade.create(this.request);

        assertNotNull(result);
        verify(this.votingService).save(this.voting);
    }

    @Test
    @Order(2)
    @DisplayName("Exceção lançada. O associado não foi encontrado.")
    void testCreateVoteAssociatedNotFound() {

        when(this.associatedService.findById(ID_ASSOCIATED)).thenReturn(Optional.empty());

        when(messagesFacade.errorAsssocietedNotFoundException()).thenThrow(new NotFoundException("message.error.associated-not-found"));
        var exception = assertThrows(NotFoundException.class, () -> this.votingFacade.create(this.request));

        assertEquals("message.error.associated-not-found", exception.getMessage());
        verify(this.votingConverter, never()).converterToEntity(any(VotingRequest.class), any(Associated.class), any(Session.class));
        verify(this.votingConverter, never()).converterToResponse(any(Voting.class));
        verify(this.votingService, never()).save(any(Voting.class));

    }

    @Test
    @Order(3)
    @DisplayName("Exceção lançada. A sessão não foi encontrada.")
    void testCreateVoteSessionNotFound() {

        when(this.associatedService.findById(ID_ASSOCIATED)).thenReturn(Optional.of(this.associated));
        when(this.sessionService.findById(ID_SESSION)).thenReturn(Optional.empty());

        when(messagesFacade.errorSessionNotFoundException()).thenThrow(new NotFoundException("message.error.session-not-found"));
        var exception = assertThrows(NotFoundException.class, () -> this.votingFacade.create(this.request));

        assertEquals("message.error.session-not-found", exception.getMessage());
        verify(this.votingConverter, never()).converterToEntity(any(VotingRequest.class), any(Associated.class), any(Session.class));
        verify(this.votingConverter, never()).converterToResponse(any(Voting.class));
        verify(this.votingService, never()).save(any(Voting.class));
    }

    @Test
    @Order(4)
    @DisplayName("Exceção lançada. A sessão está fechada para votação.")
    void testCreateVoteSessionClosed() {
        when(this.associatedService.findById(ID_ASSOCIATED)).thenReturn(Optional.of(this.associated));
        when(this.sessionService.findById(ID_SESSION)).thenReturn(Optional.of(this.session));
        when(this.sessionService.isCurrentTimeWithinSession(ID_SESSION)).thenReturn(false);

        when(messagesFacade.errorSessionIsClosed()).thenThrow(new BadRequestException("message.error.session-closed"));
        var exception = assertThrows(BadRequestException.class, () -> votingFacade.create(request));

        assertEquals("message.error.session-closed", exception.getMessage());
        verify(this.votingConverter, never()).converterToEntity(any(VotingRequest.class), any(Associated.class), any(Session.class));
        verify(this.votingConverter, never()).converterToResponse(any(Voting.class));
        verify(this.votingService, never()).save(any(Voting.class));

    }

    @Test
    @Order(5)
    @DisplayName("Exceção lançada. O associado já votou nesta sessão.")
    void testCreateVoteAssociatedAlreadyVoted() {
        when(this.associatedService.findById(ID_ASSOCIATED)).thenReturn(Optional.of(this.associated));
        when(this.sessionService.findById(ID_SESSION)).thenReturn(Optional.of(this.session));
        when(this.sessionService.isCurrentTimeWithinSession(ID_SESSION)).thenReturn(true);
        when(this.votingService.findById(any())).thenReturn(Optional.of(this.voting));

        when(messagesFacade.errorAssociatedAlreadyVoted()).thenThrow(new BadRequestException("message.error.associated-already-voted"));
        var exception = assertThrows(BadRequestException.class, () -> this.votingFacade.create(this.request));

        assertEquals("message.error.associated-already-voted", exception.getMessage());
        verify(this.votingConverter, never()).converterToEntity(any(VotingRequest.class), any(Associated.class), any(Session.class));
        verify(this.votingConverter, never()).converterToResponse(any(Voting.class));
        verify(this.votingService, never()).save(any(Voting.class));

    }

    @Test
    @Order(6)
    @DisplayName("Contagem de votos com sucesso.")
    void testCountVotesSuccess() {

        when(this.sessionService.isCurrentTimeWithinSession(ID_SESSION)).thenReturn(false);

        Map<VoteEnum, Long> voteCount = new EnumMap<>(VoteEnum.class);
        voteCount.put(VoteEnum.YES, 10L);
        voteCount.put(VoteEnum.NO, 5L);
        when(this.votingService.countVotesBySessionId(ID_SESSION)).thenReturn(voteCount);

        Map<String, Long> result = this.votingFacade.countVotes(ID_SESSION);

        assertNotNull(result);
        assertEquals(10L, result.get("Sim"));
        assertEquals(5L, result.get("Não"));

        verify(this.sessionService, times(1)).isCurrentTimeWithinSession(ID_SESSION);
        verify(this.votingService, times(1)).countVotesBySessionId(ID_SESSION);

    }

    @Test
    @Order(7)
    @DisplayName("Exceção lançada. A sessão ainda está aberta para contagem de votos.")
    void testCountVotesSessionStillOpen() {

        when(this.sessionService.isCurrentTimeWithinSession(ID_SESSION)).thenReturn(true);

        when(messagesFacade.errorSessionIsClosedForVoteCounting()).thenThrow(new BadRequestException("message.error.session-closed-vote-couting"));
        var exception = assertThrows(BadRequestException.class, () -> this.votingFacade.countVotes(ID_SESSION));

        assertEquals("message.error.session-closed-vote-couting", exception.getMessage());
        verify(this.sessionService, times(1)).isCurrentTimeWithinSession(ID_SESSION);
        verify(this.votingService, never()).countVotesBySessionId(ID_SESSION);

    }

    void loadObjects() {
        this.request = new VotingRequest(ID_ASSOCIATED, ID_SESSION, VoteEnum.YES); // <- ORDEM CORRIGIDA AQUI
        this.associated = Associated.builder().id(ID_ASSOCIATED).build();
        this.session = Session.builder()
                .id(ID_SESSION)
                .start(LocalDateTime.now().minusMinutes(1))
                .end(LocalDateTime.now().plusMinutes(5))
                .build();
        this.voting = Voting.builder()
                .pk(VotingPk.builder().associated(this.associated).session(this.session).build())
                .vote(VoteEnum.YES)
                .createdAt(LocalDateTime.now())
                .build();
        this.response = mock(VotingResponse.class);
    }
}
