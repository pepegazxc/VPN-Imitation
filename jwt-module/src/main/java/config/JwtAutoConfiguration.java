package config;

import jwt.JwtProvider;
import jwt.JwtValidation;
import jwt.KeyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAutoConfiguration {
    @Bean
    public JwtProvider jwtProvider(@Value("${jwt.private-key-base64:}") String privateKey) throws Exception {
        if (privateKey.isBlank()){
            return null;
        }
        return new JwtProvider(KeyUtils.loadPrivateKey(privateKey));
    }

    @Bean
    public JwtValidation jwtValidation(@Value("${jwt.public-key-base64:}") String publicKey) throws Exception{
        if (publicKey.isBlank()){
            return null;
        }
        return new JwtValidation(KeyUtils.loadPublicKey(publicKey));
    }
}
