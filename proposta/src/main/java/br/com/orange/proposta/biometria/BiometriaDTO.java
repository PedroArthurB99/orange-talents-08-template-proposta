package br.com.orange.proposta.biometria;

import java.time.LocalDateTime;

public class BiometriaDTO {

    private Long id;
    private String imagem;
    private LocalDateTime dataCadastro;

    public BiometriaDTO(Biometria biometria) {
        this.id = biometria.getId();
        this.imagem = biometria.getImagem();
        this.dataCadastro = biometria.getDataCadastro();
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
