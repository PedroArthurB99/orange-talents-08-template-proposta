package br.com.orange.proposta.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

    public static final TextEncryptor textEncryptor = Encryptors.queryableText("documento",
            KeyGenerators.string().generateKey());

}
