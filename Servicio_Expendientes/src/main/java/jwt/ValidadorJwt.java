/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

/**
 *
 * @author norma
 */
public class ValidadorJwt {

    private static final String SECRET = "itson-proyecto-distribuidos-equipo-4-clave-secreta";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static boolean validarToken(String token, String cedula, String documento) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String cedulaToken = claims.getSubject();
            String documentoToken = claims.get("documento", String.class);

            System.out.println("Token válido. Cédula: " + cedulaToken + " Documento: " + documentoToken);
            return cedulaToken.equals(cedula) && documentoToken.equals(documento);

        } catch (Exception e) {
            System.err.println("Token inválido o expirado: " + e.getMessage());
            return false;
        }
    }
}
