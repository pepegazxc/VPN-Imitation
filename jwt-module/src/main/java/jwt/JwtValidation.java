package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;

public class JwtValidation {

    private final PublicKey publicKey;

    public JwtValidation(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Claims validateAndExctractClaims(String token){
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
