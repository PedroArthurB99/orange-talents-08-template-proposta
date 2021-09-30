package br.com.orange.proposta.apisexternas.cartoes;

public class DadosParaCarteira {

    private String email;
    private String carteira;

    @Deprecated
    public DadosParaCarteira() {}

    public DadosParaCarteira(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

}
