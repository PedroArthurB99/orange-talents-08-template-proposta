package br.com.orange.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraForm {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteiraEnum tipoCarteiraEnum;

    public Carteira toModel() {
        return new Carteira(this.email, tipoCarteiraEnum);
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraEnum getTipoCarteiraEnum() {
        return tipoCarteiraEnum;
    }
}
