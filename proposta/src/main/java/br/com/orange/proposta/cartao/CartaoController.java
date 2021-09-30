package br.com.orange.proposta.cartao;

import br.com.orange.proposta.apisexternas.cartoes.APIExternaCartoes;
import br.com.orange.proposta.avisoviagem.AvisoViagem;
import br.com.orange.proposta.avisoviagem.AvisoViagemForm;
import br.com.orange.proposta.biometria.Biometria;
import br.com.orange.proposta.biometria.BiometriaForm;
import br.com.orange.proposta.bloqueio.Bloqueio;
import br.com.orange.proposta.carteira.Carteira;
import br.com.orange.proposta.carteira.CarteiraForm;
import br.com.orange.proposta.exception.EntidadeNaoEncontrada;
import br.com.orange.proposta.exception.ObjetoErroDTO;
import br.com.orange.proposta.exception.RegraNegocioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
@Transactional
public class CartaoController {

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private APIExternaCartoes apiExternaCartoes;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/biometria")
    public ResponseEntity<CartaoDTO> adicionarBiometria(@PathVariable("id") Long id, @RequestBody BiometriaForm form,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new RegraNegocioException(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        Biometria biometria = form.toModel();
        cartao.addBiometria(biometria);
        this.repository.save(cartao);
        URI uri = uriComponentsBuilder.path("/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDTO(cartao));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/bloqueio")
    public void bloquearCartao(@PathVariable("id") Long id, HttpServletRequest request) throws JsonProcessingException {
        Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader(HttpHeaders.USER_AGENT));
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        cartao.addBloqueio(bloqueio, apiExternaCartoes);
        this.repository.save(cartao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/viagem")
    public void avisoViagem(@PathVariable("id") Long id, HttpServletRequest request, @RequestBody @Valid AvisoViagemForm form) throws JsonProcessingException {
        AvisoViagem viagem = form.toModel(request);
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        cartao.addAvisoViagem(viagem, apiExternaCartoes);
        this.repository.save(cartao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}/carteira")
    public ResponseEntity<CartaoDTO> adicionarCarteira(@PathVariable("id") Long id, @RequestBody @Valid CarteiraForm form,
                                                       UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        Cartao cartao = this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(
                new ObjetoErroDTO("id Cartão", "Não existe um cartão com esse id")));
        Carteira carteira = form.toModel();
        cartao.adicionarCarteira(carteira, apiExternaCartoes);
        this.repository.save(cartao);
        URI uri = uriComponentsBuilder.path("/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDTO(cartao));
    }

}
