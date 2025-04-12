package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "topic", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Topic implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 30)
    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @NotBlank
    @Size(min = 10)
    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    private String description;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "open", nullable = false)
    private boolean open;

    @OneToOne(mappedBy = "topic", cascade = CascadeType.ALL, optional = false)
    private Session session;

}
