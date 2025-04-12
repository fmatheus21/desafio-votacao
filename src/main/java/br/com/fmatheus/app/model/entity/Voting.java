package br.com.fmatheus.app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "vote", nullable = false)
    private String vote;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
