package br.com.fmatheus.app.controller.enums;

import lombok.Getter;

@Getter
public enum VoteEnum {
    YES("Sim"),
    NO("Não");

    private final String value;

    VoteEnum(String value) {
        this.value = value;
    }
}


