package br.com.orange.proposta.biometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imagem;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Biometria() {}

    public Biometria(String imagem) {
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public String getImagem() {
        return imagem;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
