package br.com.orange.proposta.apisexternas.cartoes;

import br.com.orange.proposta.cartao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResultadoCartao {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

    @Deprecated
    public ResultadoCartao() {}

    public ResultadoCartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite, LocalDateTime vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public Cartao toCartao() {
        Cartao cartao = new Cartao(this.id, this.emitidoEm, this.titular, this.limite);
        return cartao;
    }

    public String getId() {
        return id;
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
