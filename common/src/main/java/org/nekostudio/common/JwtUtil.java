package org.nekostudio.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.nekostudio.entity.AppUser;
import org.nekostudio.entity.Role;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
public class JwtUtil {
    private final static String SECRETYKEY = "defaultkeyqweasdzxc";
    private final static Date date = new Date();


    public static String createTokenByUserSalt(AppUser appUser) {
        Algorithm algorithm = Algorithm.HMAC256(appUser.getSalt());
        return create(appUser, algorithm);
    }

    private static String create(AppUser appUser, Algorithm algorithm) {
        List<String> roles = appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        Date date = Date.from(Instant.now().plusSeconds(3000L));
        return JWT.create().withIssuer("neko.studio")
                .withClaim("userid", appUser.getId())
                .withClaim("authority", roles)
                .withSubject(appUser.getUsername())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static String createTokenByDefaultKey(AppUser appUser) {
        Algorithm algorithm = Algorithm.HMAC256(SECRETYKEY);
        return create(appUser, algorithm);
    }
    public static String getSubject(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getSubject();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getUserId(String token) {
        try {
            return JWT.decode(token).getClaim("uuid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static Claim getRole(String token) {
        try {
             return JWT.decode(token).getClaim("authority");
        }catch (Exception e) {
            return null;
        }
    }
    public static List<String> getRoles(String token) {
        Claim claim = getRole(token);
        if (claim !=null) {
            return  claim.asList(String.class);
        }
        return Collections.emptyList();
    }
    public static void validToken(String token, String secret) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
    }
    public static boolean validToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRETYKEY);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            jwtVerifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            return false;
        }
    }
}
