package br.com.orange.proposta.apisexternas.cartoes;

import br.com.orange.proposta.apisexternas.solicitacao.DadosSolicitante;
import br.com.orange.proposta.proposta.Proposta;
import br.com.orange.proposta.proposta.PropostaRepository;
import br.com.orange.proposta.proposta.StatusPropostaEnum;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarCartaoAprovado {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private APIExternaCartoes apiExternaCartoes;

    @Scheduled(fixedDelayString = "${proposta-timer}")
    public void associarCartao() {
        List<Proposta> propostas = this.propostaRepository.findByStatusAndCartaoIsNull(StatusPropostaEnum.ELEGIVEL);
        propostas.forEach(proposta -> {
            try {
                DadosSolicitante dadosSolicitante = new DadosSolicitante(proposta.getDocumento(), proposta.getNome(),
                        String.valueOf(proposta.getId()));
                ResultadoCartao solicitacao = this.apiExternaCartoes.solicitar(dadosSolicitante);
                proposta.adicionarCartao(solicitacao.toCartao());
                propostaRepository.save(proposta);
                System.out.println("chegou aq");
            } catch (FeignException exception) {
                System.out.println("Deu errado, " + exception.getMessage());
            }

        });
    }
}
