package br.com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "document"})})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 70)
    @Column(name = "name", length = 70, nullable = false)
    private String name;

    @NotBlank
    @Size(min = 11, max = 20)
    @Column(name = "document", length = 20, nullable = false)
    private String document;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = false)
    private Associated associated;
}
