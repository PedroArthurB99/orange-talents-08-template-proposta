package br.com.orange.proposta.carteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @Deprecated
    public Carteira() {}

    @Enumerated(EnumType.STRING)
    private TipoCarteiraEnum tipoCarteira;

    public Carteira(String email, TipoCarteiraEnum tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraEnum getTipoCarteira() {
        return tipoCarteira;
    }
}
