package br.com.orange.proposta.cartao;

import br.com.orange.proposta.biometria.Biometria;
import br.com.orange.proposta.biometria.BiometriaForm;
import br.com.orange.proposta.bloqueio.Bloqueio;
import br.com.orange.proposta.exception.EntidadeNaoEncontrada;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/bloqueio")
    public void bloquearCartao(@PathVariable("id") Long id, HttpServletRequest request) {
        Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader(HttpHeaders.USER_AGENT));
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        cartao.addBloqueio(bloqueio);
        this.repository.save(cartao);
    }

}
