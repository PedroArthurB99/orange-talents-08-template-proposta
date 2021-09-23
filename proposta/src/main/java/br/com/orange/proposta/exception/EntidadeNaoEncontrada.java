package br.com.orange.proposta.exception;

public class EntidadeNaoEncontrada extends RuntimeException{

    private ObjetoErroDTO objetoErroDTO;

    public EntidadeNaoEncontrada(ObjetoErroDTO objetoErroDTO) {
        this.objetoErroDTO = objetoErroDTO;
    }

    public ObjetoErroDTO getObjetoErroDTO() {
        return objetoErroDTO;
    }
}
