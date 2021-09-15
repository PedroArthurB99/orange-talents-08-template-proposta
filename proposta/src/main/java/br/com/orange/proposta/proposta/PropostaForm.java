package br.com.orange.proposta.proposta;

import br.com.orange.proposta.validation.DocumentoValido;
import br.com.orange.proposta.validation.UmaPropostaPorDocumento;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaForm {

    @NotBlank
    @Size(min = 11, max = 14)
    @DocumentoValido
    @UmaPropostaPorDocumento
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
