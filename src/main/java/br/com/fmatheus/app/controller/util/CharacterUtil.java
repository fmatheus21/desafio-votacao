package br.com.fmatheus.app.controller.util;


import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;

import java.util.Objects;

/**
 * Classe utilitária para manipulação de caracteres.
 * <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
public class CharacterUtil extends CapitalizeUtil {

    private CharacterUtil() {
        super();
    }

    /**
     * Converte a string para formato capitalizado (primeira letra maiúscula de cada palavra) e remove espaços duplicados.
     *
     * @param value String de entrada.
     * @return String formatada ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String convertFirstUppercaseCharacter(String value) {
        return Objects.nonNull(value) ? capitalizeFully(removeDuplicateSpace(value)).trim() : null;
    }

    /**
     * Converte todos os caracteres para maiúsculas e remove espaços duplicados.
     *
     * @param value String de entrada.
     * @return String formatada ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String convertAllUppercaseCharacters(String value) {
        return Objects.nonNull(value) ? removeDuplicateSpace(value.toUpperCase().trim()) : null;
    }

    /**
     * Converte todos os caracteres para minúsculas e remove espaços duplicados.
     *
     * @param value String de entrada.
     * @return String formatada ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String convertAllLowercaseCharacters(String value) {
        return Objects.nonNull(value) ? removeDuplicateSpace(value.toLowerCase().trim()) : null;
    }

    /**
     * Remove todos os espaços da string.
     *
     * @param value String de entrada.
     * @return String sem espaços ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String removeAllSpaces(String value) {
        return Objects.nonNull(value) ? value.replace(" ", "") : null;
    }

    /**
     * Remove espaços duplicados da string, mantendo apenas um espaço entre palavras.
     *
     * @param value String de entrada.
     * @return String com espaços únicos ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String removeDuplicateSpace(String value) {
        return Objects.nonNull(value) ? value.replaceAll("\\s+", " ").trim() : null;
    }

    /**
     * Remove todos os caracteres especiais, mantendo apenas letras e números.
     *
     * @param value String de entrada.
     * @return String com caracteres alfanuméricos ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String removeSpecialCharacters(String value) {
        return Objects.nonNull(value) ? value.replaceAll("[^a-zA-Z0-9]", "") : null;
    }

    /**
     * Aplica uma máscara à string numérica fornecida.
     * Caracteres '#' na máscara são substituídos por dígitos da string.
     *
     * @param valor   Valor numérico como string (pode conter caracteres não numéricos).
     * @param mascara Máscara no formato desejado, com '#' representando dígitos.
     * @return String formatada com a máscara.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String formatMask(String valor, String mascara) {
        StringBuilder dado = new StringBuilder();
        for (var i = 0; i < valor.length(); i++) {
            var c = valor.charAt(i);
            if (Character.isDigit(c)) {
                dado.append(c);
            }
        }
        int indMascara = mascara.length();
        int indCampo = dado.length();

        while (indCampo > 0 && indMascara > 0) {
            if (mascara.charAt(--indMascara) == '#') {
                indCampo--;
            }
        }

        StringBuilder saida = new StringBuilder();
        for (; indMascara < mascara.length(); indMascara++) {
            saida.append((mascara.charAt(indMascara) == '#') ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
        }
        return saida.toString();
    }

    /**
     * Formata uma string contendo CPF no formato XXX.XXX.XXX-XX.
     *
     * @param value CPF como string numérica.
     * @return CPF formatado ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String formatCPF(String value) {
        return Objects.nonNull(value) ? new CPFFormatter().format(value) : null;
    }

    /**
     * Formata uma string contendo CNPJ no formato XX.XXX.XXX/XXXX-XX.
     *
     * @param value CNPJ como string numérica.
     * @return CNPJ formatado ou {@code null} se a entrada for nula.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public static String formatCNPJ(String value) {
        return Objects.nonNull(value) ? new CNPJFormatter().format(value) : null;
    }

}