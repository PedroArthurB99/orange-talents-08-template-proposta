package br.com.orange.proposta.exception;

public class JaExistePropostaComODocumentoException extends RuntimeException {

    private ObjetoErroDTO objetoErroDTO;

    public JaExistePropostaComODocumentoException(ObjetoErroDTO objetoErroDTO) {
        this.objetoErroDTO = objetoErroDTO;
    }

    public ObjetoErroDTO getObjetoErroDTO() {
        return objetoErroDTO;
    }
}
