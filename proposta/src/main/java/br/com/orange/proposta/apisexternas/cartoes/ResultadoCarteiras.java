package br.com.orange.proposta.apisexternas.cartoes;

public class ResultadoCarteiras {

    private String resultado;
    private String id;

    @Deprecated
    public ResultadoCarteiras() {}

    public ResultadoCarteiras(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
