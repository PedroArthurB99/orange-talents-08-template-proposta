package br.com.orange.proposta.apisexternas.cartoes;

import br.com.orange.proposta.apisexternas.solicitacao.DadosSolicitante;
import br.com.orange.proposta.apisexternas.solicitacao.ResultadoSolicitacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "sistema-cartoes", url = "${feign.client.api-externa-cartoes}")
public interface APIExternaCartoes {

    @PostMapping
    public ResultadoCartao solicitar(@RequestBody @Valid DadosSolicitante dadosSolicitante);
}
