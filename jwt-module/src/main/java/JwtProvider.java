import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;

public class JwtProvider {

    private final PrivateKey privateKey;

    public JwtProvider(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateToken(String userId, String role, Duration ttl){
        return Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(ttl)))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
