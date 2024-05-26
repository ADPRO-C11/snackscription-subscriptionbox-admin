package id.ac.ui.cs.advprog.snackscription_subscriptionbox.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JWTUtilsTest {

    private JWTUtils jwtUtils;

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @BeforeEach
    void setUp() {
        jwtUtils = new JWTUtils(jwtSecret);
    }

    @Test
    void testExtractRole() {
        String token = createToken("admin", new Date(System.currentTimeMillis() + 1000 * 60 * 60)); // 1 hour validity
        String role = jwtUtils.extractRole(token);
        assertEquals("admin", role);
    }

    @Test
    void testIsTokenValid() {
        String token = createToken("admin", new Date(System.currentTimeMillis() + 1000 * 60 * 60)); // 1 hour validity
        assertTrue(jwtUtils.isTokenValid(token));
    }


    @Test
    void testIsTokenExpiredFalse() {
        String token = createToken("admin", new Date(System.currentTimeMillis() + 1000 * 60 * 60)); // 1 hour validity
        assertFalse(jwtUtils.isTokenExpired(token));
    }

    private String createToken(String role, Date expiration) {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");

        return Jwts.builder()
                .claim("role", role)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
