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

    @ManyToOne
    @JoinColumn(name = "id_associeted", referencedColumnName = "id", insertable = false, updatable = false)
    private Associeted associeted;

    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id", insertable = false, updatable = false)
    private Session session;
}
