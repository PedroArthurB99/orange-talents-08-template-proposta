package br.com.orange.proposta.apisexternas.cartoes;

import br.com.orange.proposta.apisexternas.solicitacao.DadosSolicitante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "sistema-cartoes", url = "${feign.client.api-externa-cartoes}")
public interface APIExternaCartoes {

    @PostMapping
    public ResultadoCartao solicitar(DadosSolicitante dadosSolicitante);

    @PostMapping("/{id}/bloqueios")
    public ResultadoBloqueio bloquear(@PathVariable("id") String id, DadosParaBloqueio dadosParaBloqueio);

    @PostMapping("/{id}/avisos")
    public ResultadoAviso avisoViagem(@PathVariable("id") String id, DadosParaAviso dadosParaAviso);

    @PostMapping("/{id}/carteiras")
    public ResultadoCarteiras adicionarCarteira(@PathVariable("id") String id, DadosParaCarteira dadosParaCarteira);
}
