package br.com.orange.proposta.exception;

public class RegraNegocioException extends RuntimeException{

    private ObjetoErroDTO objetoErroDTO;

    public RegraNegocioException(ObjetoErroDTO objetoErroDTO) {
        this.objetoErroDTO = objetoErroDTO;
    }

    public ObjetoErroDTO getObjetoErroDTO() {
        return objetoErroDTO;
    }
}
