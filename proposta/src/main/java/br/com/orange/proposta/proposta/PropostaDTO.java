package br.com.orange.proposta.proposta;

import br.com.orange.proposta.cartao.CartaoDTO;
import br.com.orange.proposta.util.Ofuscador;

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
        this.documento = Ofuscador.ofuscarString(proposta.getDocumento());
        this.email = Ofuscador.ofuscarString(proposta.getEmail());
        this.nome = Ofuscador.ofuscarString(proposta.getNome());
        this.endereco = Ofuscador.ofuscarString(proposta.getEndereco());
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
