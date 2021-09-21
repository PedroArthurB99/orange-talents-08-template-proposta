package br.com.orange.proposta.proposta;

import br.com.orange.proposta.apisexternas.solicitacao.APIExternaSolicitacao;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
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
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PropostaDTO> cadastrar(@RequestBody @Valid PropostaForm form, UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        Proposta proposta = form.toModel();
        proposta.analisarProposta(this.apiExternaSolicitacao);
        this.repository.save(proposta);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDTO(proposta));
    }

    @GetMapping
    @RequestMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public PropostaDTO detalhar(@PathVariable("id") Long id) {
        Proposta proposta = this.repository.findById(id).orElseThrow(() ->new RegraNegocioException(
                new ObjetoErroDTO("id-" + id, "Não existe um produto com esse id.")));

        return new PropostaDTO(proposta);
    }
}
