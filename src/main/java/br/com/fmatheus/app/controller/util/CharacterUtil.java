package br.com.fmatheus.app.controller.util;


import java.util.Objects;

public class CharacterUtil {

    private CharacterUtil() {
        throw new IllegalStateException(getClass().getName());
    }

    public static String removeSpecialCharacters(String value) {
        return Objects.nonNull(value) ? value.replaceAll("[^a-zA-Z0-9]", "") : null;
    }


}
