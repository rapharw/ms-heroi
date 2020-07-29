package br.com.ey.msheroi.config.security.service;

import br.com.ey.msheroi.config.security.vo.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import br.com.ey.msheroi.vo.Usuario;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static br.com.ey.msheroi.config.security.utils.EncryptDecryptUtils.decrypt;

@Component
public class TokenServiceImpl implements TokenService {

    private static final String JWT_SECRET = "mKcyx1FzgVqoUWw23q7JXm7rcFL/oJ6AhJa8AutyVmzvIqy/wzrqIzt0tjs9aGtlctX8HHZco6o=";

    @Override
    public String generateToken(Usuario usuario) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        Key key = Keys.hmacShaKeyFor(decrypt(JWT_SECRET).getBytes());

        return Jwts.builder()
                    .claim("id", usuario.getId())
                    .claim("sub", usuario.getUsername())
                    .claim("admin", usuario.isAdmin())
                    .setExpiration(expirationDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
    }

    private Jws<Claims> getJwsClaims(String token){
        byte[] secretBytes = decrypt(JWT_SECRET).getBytes();

        return Jwts.parserBuilder()
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(token);
    }

    @Override
    public UserPrincipal parseToken(String token) {

        Jws<Claims> jwsClaims = getJwsClaims(token);

        String username = jwsClaims.getBody().getSubject();
        Integer userId = jwsClaims.getBody().get("id", Integer.class);
        boolean isAdmin = jwsClaims.getBody().get("admin", Boolean.class);

        return new UserPrincipal(userId, username, isAdmin);
    }

}
