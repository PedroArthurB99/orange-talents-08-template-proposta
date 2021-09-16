package br.com.orange.proposta.apisexternas.solicitacao;

public class ResultadoSolicitacao {

    private String documento;
    private String nome;
    private ResultadoSolicitacaoEnum resultadoSolicitacao;
    private String idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public ResultadoSolicitacaoEnum getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
