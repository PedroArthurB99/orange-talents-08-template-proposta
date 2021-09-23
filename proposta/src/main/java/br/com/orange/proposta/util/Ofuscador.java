package br.com.orange.proposta.util;

import org.springframework.stereotype.Component;

@Component
public class Ofuscador {

    public static String ofuscarString(String texto) {
        String novaString = "";
        double limite = texto.length() * 0.75;
        for (int i = 0; i < (int) texto.length(); i ++) {
            if (i < limite) {
                novaString += "*";
            }
            else {
                novaString += texto.charAt(i);
            }
        }
        return novaString;
    }
}
