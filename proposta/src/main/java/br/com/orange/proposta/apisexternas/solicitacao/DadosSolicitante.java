package br.com.orange.proposta.apisexternas.solicitacao;

import javax.validation.constraints.NotBlank;

public class DadosSolicitante {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    @Deprecated
    public DadosSolicitante() {}

    public DadosSolicitante(@NotBlank String documento, @NotBlank String nome, @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
