package br.com.orange.proposta.avisoviagem;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AvisoViagemForm {

    @NotBlank
    private String destinoViagem;

    @NotNull @FutureOrPresent
    private LocalDateTime dataTermino;

    public AvisoViagem toModel(HttpServletRequest request) {
        return new AvisoViagem(this.destinoViagem, this.dataTermino,
                request.getLocalAddr(), request.getHeader(HttpHeaders.USER_AGENT));
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }
}
