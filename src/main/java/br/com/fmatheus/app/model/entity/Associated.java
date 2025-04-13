package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "associated", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "document", "id_person"})})
public class Associated implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private UUID id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    private Person person;


}
