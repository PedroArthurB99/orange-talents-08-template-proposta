package br.com.orange.proposta.biometria;

import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaForm {

    @NotBlank
    private String imagem;

    public Biometria toModel() {
        if (validarBase64(this.imagem)) {
            return new Biometria(this.imagem);
        }
        else {
            throw new RegraNegocioException(new ObjetoErroDTO("imagem", "Você enviou uma base64 inválida"));
        }
    }

    public boolean validarBase64(String imagem) {
        byte[] fingerprintByte = Base64.getDecoder().decode(this.imagem);
        String fingerprintConvert = Base64.getEncoder().encodeToString(fingerprintByte);
        return this.imagem.equals(fingerprintConvert);
    }

    public String getImagem() {
        return imagem;
    }
}
