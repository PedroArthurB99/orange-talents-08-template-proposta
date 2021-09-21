package br.com.orange.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoDTO {

    private Long id;
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

    public CartaoDTO(Cartao cartao) {
        this.id = cartao.getId();
        this.numeroCartao = cartao.getNumeroCartao();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
