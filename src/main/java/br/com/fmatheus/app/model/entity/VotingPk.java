package br.com.fmatheus.app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VotingPk implements Serializable {

    @Column(name = "id_associeted", nullable = false)
    private UUID idAssocieted;

    @Column(name = "id_topic", nullable = false)
    private UUID idTopic;
}
