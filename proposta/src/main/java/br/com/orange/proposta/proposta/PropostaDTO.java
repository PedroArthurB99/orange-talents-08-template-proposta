package br.com.orange.proposta.proposta;

import br.com.orange.proposta.cartao.CartaoDTO;

import java.math.BigDecimal;
import java.util.Objects;

public class PropostaDTO {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private CartaoDTO cartao;

    public PropostaDTO(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEmail();
        this.salario = proposta.getSalario();
        if (Objects.nonNull(proposta.getCartao())) {
            this.cartao = new CartaoDTO(proposta.getCartao());
        }
    }

    public Long getId() {
        return id;
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

    public CartaoDTO getCartao() {
        return cartao;
    }
}
