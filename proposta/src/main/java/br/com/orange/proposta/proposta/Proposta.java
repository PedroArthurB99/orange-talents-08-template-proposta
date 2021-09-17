package br.com.orange.proposta.proposta;

import br.com.orange.proposta.apisexternas.solicitacao.APIExternaSolicitacao;
import br.com.orange.proposta.apisexternas.solicitacao.DadosSolicitante;
import br.com.orange.proposta.apisexternas.solicitacao.ResultadoSolicitacao;
import br.com.orange.proposta.apisexternas.solicitacao.ResultadoSolicitacaoEnum;
import br.com.orange.proposta.cartao.Cartao;
import br.com.orange.proposta.validation.DocumentoValido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 11, max = 14)
    @DocumentoValido
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull @Positive
    private BigDecimal salario;

    private StatusPropostaEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    @Deprecated
    public Proposta() {}

    public Proposta(@NotBlank String documento, @NotBlank String email, @NotBlank String nome, @NotBlank String endereco,
                    @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
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

    public void analisarProposta(APIExternaSolicitacao apiExternaSolicitacao) throws JsonProcessingException {
        DadosSolicitante dadosSolicitante = new DadosSolicitante(this.getDocumento(), this.getNome(), String.valueOf(this.getId()));
        ResultadoSolicitacao solicitacao;
        try {
            solicitacao = apiExternaSolicitacao.solicitar(dadosSolicitante);
        }
        catch (FeignException feignException) {
            ObjectMapper objectMapper = new ObjectMapper();
            solicitacao = objectMapper.readValue(feignException.contentUTF8(), ResultadoSolicitacao.class);
        }
        this.status = solicitacao.getResultadoSolicitacao() == ResultadoSolicitacaoEnum.COM_RESTRICAO ? StatusPropostaEnum.NAO_ELEGIVEL
                : StatusPropostaEnum.ELEGIVEL;
        System.out.println(this);
    }

    public void adicionarCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", status=" + status +
                '}';
    }
}
