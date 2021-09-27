package br.com.orange.proposta.apisexternas.cartoes;

import java.time.LocalDateTime;

public class DadosParaAviso {

    private String destino;
    private LocalDateTime validoAte;

    @Deprecated
    public DadosParaAviso() {
    }

    public DadosParaAviso(String destino, LocalDateTime validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }
}
