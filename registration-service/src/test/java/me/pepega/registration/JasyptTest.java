package me.pepega.registration;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.StringFixedIvGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JasyptTest {

    @Test
    public void shouldGenerateEqualsCipher(){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setIvGenerator(new StringFixedIvGenerator("iv_12345678901234"));
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setSaltGenerator(new StringFixedSaltGenerator("salt_12345678901"));
        encryptor.setPassword("test-password");

        String firstString = "fakeString";
        String secondString = "fakeString";

        String firstCipher = encryptor.encrypt(firstString);
        String secondCipher = encryptor.encrypt(secondString);

        assertEquals(
                encryptor.decrypt(firstCipher),
                encryptor.decrypt(secondCipher),
                "Decrypt must be equals"
                );
        assertEquals(firstCipher, secondCipher, "Ciphers must be equals");
    }
}
