package br.com.orange.proposta.avisoviagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destinoViagem;

    @NotNull @FutureOrPresent
    private LocalDateTime dataTermino;

    private LocalDateTime instanteAvisoViagem = LocalDateTime.now();

    private String ip;

    private String userAgent;

    @Deprecated
    public AvisoViagem() {}

    public AvisoViagem(@NotBlank String destinoViagem, @NotNull LocalDateTime dataTermino, String ip, String userAgent) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getInstanteAvisoViagem() {
        return instanteAvisoViagem;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
