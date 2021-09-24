package br.com.orange.proposta.apisexternas.cartoes;

import javax.validation.constraints.NotBlank;

public class DadosParaBloqueio {

    @NotBlank
    private String sistemaResponsavel;

    @Deprecated
    public DadosParaBloqueio() {}

    public DadosParaBloqueio(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
