package me.pepega.registration.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.error.exception.cryptography.DecryptException;
import me.pepega.registration.error.exception.cryptography.EncryptException;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FieldEncryptor {

    private final StringEncryptor encryptor;

    public String encrypt(String value) {
        if (value == null) return null;
        try{
            return encryptor.encrypt(value);
        }catch (EncryptionOperationNotPossibleException ex){
            log.error("Encryption failed", ex);
            throw new EncryptException();
        }

    }

    public String decrypt(String value) {
        if (value == null) return null;
        try{
            return encryptor.decrypt(value);
        }catch (EncryptionOperationNotPossibleException ex){
            log.error("Encryption failed", ex);
            throw new DecryptException();
        }
    }
}
