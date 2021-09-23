package br.com.orange.proposta.bloqueio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    private LocalDateTime instanteBloqueio = LocalDateTime.now();

    @Deprecated
    public Bloqueio() {}

    public Bloqueio(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
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
