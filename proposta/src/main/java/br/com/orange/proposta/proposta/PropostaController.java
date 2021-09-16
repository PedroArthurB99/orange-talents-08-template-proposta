package br.com.orange.proposta.proposta;

import br.com.orange.proposta.apisexternas.solicitacao.APIExternaSolicitacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private APIExternaSolicitacao apiExternaSolicitacao;

    @PostMapping
    public ResponseEntity<PropostaDTO> cadastrar(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        Proposta proposta = form.toModel();
        proposta.analisarProposta(this.apiExternaSolicitacao);
        this.repository.save(proposta);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDTO(proposta));
    }
}
