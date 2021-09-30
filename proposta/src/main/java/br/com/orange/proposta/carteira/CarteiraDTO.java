package br.com.orange.proposta.carteira;

import br.com.orange.proposta.util.Ofuscador;

public class CarteiraDTO {

    private Long id;
    private String email;
    private TipoCarteiraEnum tipoCarteiraEnum;

    public CarteiraDTO(Carteira carteira) {
        this.id = carteira.getId();
        this.email = Ofuscador.ofuscarString(carteira.getEmail());
        this.tipoCarteiraEnum = carteira.getTipoCarteira();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraEnum getTipoCarteiraEnum() {
        return tipoCarteiraEnum;
    }
}
