package br.com.orange.proposta.cartao;

import br.com.orange.proposta.biometria.BiometriaDTO;
import br.com.orange.proposta.bloqueio.BloqueioDTO;
import br.com.orange.proposta.util.Ofuscador;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartaoDTO {

    private Long id;
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private List<BiometriaDTO> biometrias = new ArrayList<>();
    private List<BloqueioDTO> bloqueios = new ArrayList<>();
    private StatusBloqueioEnum status;

    public CartaoDTO(Cartao cartao) {
        this.id = cartao.getId();
        this.numeroCartao = Ofuscador.ofuscarString(cartao.getNumeroCartao());
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
        this.status = cartao.getStatus();
        preencherBiometrias(cartao);
        preencherBloqueios(cartao);
    }

    private void preencherBiometrias(Cartao cartao) {
        if ((cartao.getBiometrias() != null) && (cartao.getBiometrias().size() > 0))
        cartao.getBiometrias().forEach(biometria -> {
            this.biometrias.add(new BiometriaDTO(biometria));
        });
    }

    private void preencherBloqueios(Cartao cartao) {
        if ((cartao.getBloqueios() != null) && (cartao.getBloqueios().size() > 0))
            cartao.getBloqueios().forEach(bloqueio -> {
                this.bloqueios.add(new BloqueioDTO(bloqueio));
            });
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

    public List<BiometriaDTO> getBiometrias() {
        return biometrias;
    }
}
