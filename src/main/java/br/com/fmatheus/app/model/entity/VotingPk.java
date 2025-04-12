package br.com.fmatheus.app.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VotingPk implements Serializable {

    /*@Column(name = "id_associeted", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private UUID idAssocieted;

    @Column(name = "id_session", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private UUID idSession;*/

    @ManyToOne
    @JoinColumn(name = "id_associeted", referencedColumnName = "id", insertable = false, updatable = false)
    private Associeted associeted;

    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id", insertable = false, updatable = false)
    private Session session;
}
