package br.com.orange.proposta.cartao;

import br.com.orange.proposta.apisexternas.cartoes.*;
import br.com.orange.proposta.apisexternas.solicitacao.APIExternaSolicitacao;
import br.com.orange.proposta.apisexternas.solicitacao.ResultadoSolicitacao;
import br.com.orange.proposta.avisoviagem.AvisoViagem;
import br.com.orange.proposta.biometria.Biometria;
import br.com.orange.proposta.bloqueio.Bloqueio;
import br.com.orange.proposta.carteira.Carteira;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<AvisoViagem> avisosViagem;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Carteira> carteiras;

    @Enumerated(EnumType.STRING)
    private StatusBloqueioEnum status;

    @Deprecated
    public Cartao() {}

    public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.status = StatusBloqueioEnum.LIBERADO;
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

    public List<AvisoViagem> getAvisosViagem() {
        return avisosViagem;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public void addBiometria(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public void addBloqueio(Bloqueio bloqueio, APIExternaCartoes apiExternaCartoes) throws JsonProcessingException {
        if (this.status.equals(StatusBloqueioEnum.BLOQUEADO)) {
            throw new RegraNegocioException(new ObjetoErroDTO("bloqueio", "O cartão já está bloqueado"));
        }
        this.getBloqueios().add(bloqueio);
        ResultadoBloqueio resultadoBloqueio;
        try {
            resultadoBloqueio = apiExternaCartoes.bloquear(
                    this.numeroCartao, new DadosParaBloqueio("Sistema de propostas"));
            if (StatusBloqueioEnum.valueOf(resultadoBloqueio.getResultado()) == StatusBloqueioEnum.BLOQUEADO) {
                this.status = StatusBloqueioEnum.BLOQUEADO;
                System.out.println("mudou status");
            }
        }
        catch (FeignException feignException) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            resultadoBloqueio = objectMapper.readValue(feignException.contentUTF8(), ResultadoBloqueio.class);
            System.out.println("tratou exceção");
        }

    }

    public void addAvisoViagem(AvisoViagem viagem, APIExternaCartoes apiExternaCartoes) throws JsonProcessingException {
        ResultadoAviso resultadoAviso;
        try {
            resultadoAviso = apiExternaCartoes.avisoViagem(
                    this.numeroCartao, new DadosParaAviso(viagem.getDestinoViagem(), viagem.getDataTermino()));
            if (resultadoAviso.getResultado().equals("CRIADO")) {
                this.avisosViagem.add(viagem);
                System.out.println("deu bom");
            }
        }
        catch (FeignException feignException) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            resultadoAviso = objectMapper.readValue(feignException.contentUTF8(), ResultadoAviso.class);
        }
    }

    public void adicionarCarteira(Carteira carteira, APIExternaCartoes apiExternaCartoes) throws JsonProcessingException {
        if (this.carteiras.contains(carteira)) {
            throw new RegraNegocioException(new ObjetoErroDTO("carteiraId",
                    "Você já adicionou este cartão à esta carteira"));
        }

        ResultadoCarteiras resultadoCarteiras;
        try {
            resultadoCarteiras = apiExternaCartoes.adicionarCarteira(
                    this.numeroCartao, new DadosParaCarteira(carteira.getEmail(), String.valueOf(carteira.getTipoCarteira())));
            if (resultadoCarteiras.getResultado().equals("ASSOCIADA")) {
                this.carteiras.add(carteira);
                System.out.println("deu bom");
            }
        }
        catch (FeignException feignException) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            resultadoCarteiras = objectMapper.readValue(feignException.contentUTF8(), ResultadoCarteiras.class);
        }
    }

}
