package br.com.fmatheus.app.model.repository.filter;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociatedFilter {
    private String name;
    private String document;
}
