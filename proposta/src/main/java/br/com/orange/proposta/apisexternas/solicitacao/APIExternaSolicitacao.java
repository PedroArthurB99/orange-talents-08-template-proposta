package br.com.orange.proposta.apisexternas.solicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "sistema-solicitacao", url = "${feign.client.api-externa-dados-solicitante}")
public interface APIExternaSolicitacao {

    @PostMapping
    public ResultadoSolicitacao solicitar(@RequestBody @Valid DadosSolicitante dadosSolicitante);
}
