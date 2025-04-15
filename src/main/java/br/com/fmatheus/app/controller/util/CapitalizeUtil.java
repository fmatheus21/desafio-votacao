package br.com.fmatheus.app.controller.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Classe utilitária abstrata para capitalização de strings.
 */
abstract class CapitalizeUtil {

    /**
     * Construtor privado para impedir a instância da classe.
     * Lança {@link IllegalStateException} ao ser chamado.
     */
    CapitalizeUtil() {
        throw new IllegalStateException(getClass().getName());
    }

    /**
     * Capitaliza totalmente uma string, tornando a primeira letra de cada palavra maiúscula e o restante minúscula.
     * Usa espaços em branco como delimitadores.
     *
     * @param str String de entrada.
     * @return String capitalizada ou a mesma string se estiver vazia ou nula.
     */
    public static String capitalizeFully(final String str) {
        return capitalizeFully(str, (char[]) null);
    }

    /**
     * Capitaliza totalmente uma string, tornando a primeira letra de cada palavra maiúscula e o restante minúscula.
     * Os delimitadores são definidos pelos caracteres passados.
     *
     * @param str        String de entrada.
     * @param delimiters Caracteres que serão usados como delimitadores de palavras.
     * @return String capitalizada ou a mesma string se estiver vazia ou os delimitadores forem nulos/vazios.
     */
    public static String capitalizeFully(String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        str = str.toLowerCase();
        return capitalize(str, delimiters);
    }

    /**
     * Capitaliza a primeira letra de cada palavra, mantendo os delimitadores como separadores.
     *
     * @param str        String de entrada.
     * @param delimiters Caracteres que indicam onde uma nova palavra começa.
     * @return String com capitalização adequada ou a mesma string se estiver vazia ou os delimitadores forem nulos/vazios.
     */
    private static String capitalize(final String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch, delimiters)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer[i] = Character.toTitleCase(ch);
                capitalizeNext = false;
            }
        }
        return new String(buffer);
    }

    /**
     * Verifica se o caractere fornecido é um delimitador.
     *
     * @param ch         Caractere a ser verificado.
     * @param delimiters Array de caracteres delimitadores.
     * @return {@code true} se o caractere for delimitador, senão {@code false}.
     */
    private static boolean isDelimiter(final char ch, final char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (final char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

}