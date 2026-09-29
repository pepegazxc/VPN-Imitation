package me.pepega.registration.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.StringFixedIvGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfiguration {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(
            @Value("app.cipher.password") String password,
            @Value("app.cipher.algorithm") String algorithm,
            @Value("app.cipher.iv") String iv,
            @Value("app.cipher.salt") String salt
    ){
        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
        enc.setPassword(password);
        enc.setAlgorithm(algorithm);
        enc.setKeyObtentionIterations(1000);
        enc.setStringOutputType("base64");
        enc.setSaltGenerator(new StringFixedSaltGenerator(salt));
        enc.setIvGenerator(new StringFixedIvGenerator(iv));
        return enc;
    }
}
