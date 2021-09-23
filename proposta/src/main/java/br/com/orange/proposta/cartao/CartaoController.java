package br.com.orange.proposta.cartao;

import br.com.orange.proposta.biometria.Biometria;
import br.com.orange.proposta.biometria.BiometriaForm;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository repository;

    @RequestMapping("/{id}/biometria")
    public ResponseEntity<CartaoDTO> adicionarBiometria(@PathVariable("id") Long id, @RequestBody BiometriaForm form,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new RegraNegocioException(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        Biometria biometria = form.toModel();
        cartao.addBiometria(biometria);
        this.repository.save(cartao);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDTO(cartao));
    }

}
