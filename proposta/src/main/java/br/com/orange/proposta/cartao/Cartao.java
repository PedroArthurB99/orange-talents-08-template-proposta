package br.com.orange.proposta.cartao;

import br.com.orange.proposta.biometria.Biometria;
import br.com.orange.proposta.bloqueio.Bloqueio;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numeroCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    private BigDecimal limite;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Biometria> biometrias;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bloqueio> bloqueios;

    @Enumerated(EnumType.STRING)
    private StatusBloqueioEnum status = StatusBloqueioEnum.LIBERADO;

    @Deprecated
    public Cartao() {}

    public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
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

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public StatusBloqueioEnum getStatus() {
        return status;
    }

    public void addBiometria(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public void addBloqueio(Bloqueio bloqueio) {
        if (this.status.equals(StatusBloqueioEnum.BLOQUEADO)) {
            throw new RegraNegocioException(new ObjetoErroDTO("bloqueio", "O cartão já está bloqueado"));
        }
        this.getBloqueios().add(bloqueio);
        this.status = StatusBloqueioEnum.BLOQUEADO;
    }
}
