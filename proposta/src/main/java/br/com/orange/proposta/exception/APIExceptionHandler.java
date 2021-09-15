package br.com.orange.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ObjetoErroDTO> handleMethodNotValid(MethodArgumentNotValidException exception) {
        List<ObjetoErroDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.stream().forEach(e -> {
            ObjetoErroDTO erro = new ObjetoErroDTO(e.getField(), e.getDefaultMessage());
            dto.add(erro);
        });
        return dto;
    }

    @ResponseStatus(code= HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({JaExistePropostaComODocumentoException.class})
    public ObjetoErroDTO handleImagemPertenceAOutroUsuário(JaExistePropostaComODocumentoException exception) {
        return exception.getObjetoErroDTO();
    }
}