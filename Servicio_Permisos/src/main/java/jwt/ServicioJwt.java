
package jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

/**
 *
 * @author norma
 */
public class ServicioJwt {

    private static final String SECRET = "itson-proyecto-distribuidos-equipo-4-clave-secreta";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generarToken(String cedula, String documento, String nivelPermiso) {
        return Jwts.builder()
                .subject(cedula)
                .claim("documento", documento)
                .claim("nivelPermiso", nivelPermiso)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(KEY)
                .compact();
    }
}
