package me.pepega.registration.user.service.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FieldEncryptorService {
    //TO DO: Create exception handler for handling an exception

    private final StringEncryptor encryptor;

    public String encrypt(String value) {
        if (value == null) return null;
        try{
            return encryptor.encrypt(value);
        }catch (EncryptionOperationNotPossibleException ex){
            log.error("Encryption failed", ex);
            throw new RuntimeException("Failed to encrypt sensitive field", ex);
        }

    }

    public String decrypt(String value) {
        if (value == null) return null;
        try{
            return encryptor.decrypt(value);
        }catch (EncryptionOperationNotPossibleException ex){
            log.error("Encryption failed", ex);
            throw new RuntimeException("Failed to decrypt sensitive field", ex);
        }
    }
}
