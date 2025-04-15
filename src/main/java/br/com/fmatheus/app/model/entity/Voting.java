package br.com.fmatheus.app.model.entity;

import br.com.fmatheus.app.controller.converter.VoteEnumConverter;
import br.com.fmatheus.app.controller.enums.VoteEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voting")
public class Voting implements Serializable {

    @EmbeddedId
    protected VotingPk pk;

    @Convert(converter = VoteEnumConverter.class)
    @NotNull
    @Column(name = "vote", nullable = false)
    private VoteEnum vote;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


}
