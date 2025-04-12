package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "id_topic"})})
public class Session implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @NotNull
    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @OneToOne
    @JoinColumn(name = "id_topic", referencedColumnName = "id", nullable = false)
    private Topic topic;
}
