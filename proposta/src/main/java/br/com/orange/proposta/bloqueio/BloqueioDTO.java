package br.com.orange.proposta.bloqueio;

import br.com.orange.proposta.util.Ofuscador;

import java.time.LocalDateTime;

public class BloqueioDTO {

    private Long id;
    private String ip;
    private String userAgent;
    private LocalDateTime instanteBloqueio;

    public BloqueioDTO(Bloqueio bloqueio) {
        this.id = bloqueio.getId();
        this.ip = Ofuscador.ofuscarString(bloqueio.getIp());
        this.userAgent = Ofuscador.ofuscarString(bloqueio.getUserAgent());
        this.instanteBloqueio = bloqueio.getInstanteBloqueio();
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }
}
