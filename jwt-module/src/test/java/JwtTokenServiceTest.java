import io.jsonwebtoken.Claims;
import jwt.JwtProvider;
import jwt.JwtValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTokenServiceTest {

    private  JwtProvider jwtProvider;
    private  JwtValidation jwtValidation;


    @BeforeEach
    void setUp() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        jwtProvider = new JwtProvider(keyPair.getPrivate());
        jwtValidation = new JwtValidation(keyPair.getPublic());
    }

    @Test
    public void shouldGenerateValidTokenWithCorrectClaims(){
        String userId = "234";
        String role = "role";
        Duration ttl = Duration.ofMinutes(15);

        String token = jwtProvider.generateToken(userId, role, ttl);

        assertNotNull(token, "Token mustn't be null");
        assertFalse(token.isBlank(), "Token mustn't be blank");

        Claims claims = jwtValidation.validateAndExctractClaims(token);

        assertNotNull(claims, "Claims mustn't be null");
        assertEquals(userId, claims.getSubject(), "Subject must be equals with userId");
        assertEquals(role, claims.get(role, String.class), "Role must be the same");
    }
}
