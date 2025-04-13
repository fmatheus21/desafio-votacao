package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class VoteEnumConverter implements AttributeConverter<VoteEnum, String> {

    @Override
    public String convertToDatabaseColumn(VoteEnum voteEnum) {
        if (voteEnum == null) return null;
        return voteEnum.getValue();
    }

    @Override
    public VoteEnum convertToEntityAttribute(String value) {
        if (value == null) return null;

        return Stream.of(VoteEnum.values())
                .filter(v -> v.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor inválido para VoteEnum: " + value));
    }
}