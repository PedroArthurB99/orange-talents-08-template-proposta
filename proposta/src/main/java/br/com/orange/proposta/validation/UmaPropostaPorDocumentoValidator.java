package br.com.orange.proposta.validation;

import br.com.orange.proposta.exception.JaExistePropostaComODocumentoException;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.proposta.Proposta;
import br.com.orange.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class UmaPropostaPorDocumentoValidator implements ConstraintValidator<UmaPropostaPorDocumento, String> {

    @Autowired
    private PropostaRepository repository;

    private String mensagem;

    @Override
    public void initialize(UmaPropostaPorDocumento constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.mensagem = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<Proposta> proposta = this.repository.findByDocumento(value);
        if (!proposta.isEmpty()) throw new JaExistePropostaComODocumentoException(new ObjetoErroDTO("documento", this.mensagem));
        else return true;
    }
}
