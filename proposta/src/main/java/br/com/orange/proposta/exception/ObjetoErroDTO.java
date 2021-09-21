package br.com.orange.proposta.exception;

import java.time.LocalDateTime;

public class ObjetoErroDTO {

    private String campo;
    private String erro;
    private LocalDateTime instanteDoErro = LocalDateTime.now();


    public ObjetoErroDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

    public LocalDateTime getInstanteDoErro() {
        return instanteDoErro;
    }

    @Override
    public String toString() {
        return "ObjetoErroDTO{" +
                "campo='" + campo + '\'' +
                ", erro='" + erro + '\'' +
                ", instanteDoErro=" + instanteDoErro +
                '}';
    }
}
