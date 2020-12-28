package ro.tuc.ds2020.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.UnAuthorizedRequestException;
import ro.tuc.ds2020.utils.Role;

@Service
public class JwtUtil {

    private static final int HOUR = 3600000;
    private static final int MINUTE = 60000;
    private static final String SECRET_KEY = "$2a$04$BdthxWaO6FR7YtqKF9wh5urSSCcsErU/CXQFP/PFdEFQAYFJvJGky";
    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        List<Role> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        roles.add(Role.valueOf(authorities.iterator().next().toString()));
        return createToken(userDetails.getUsername(), roles);
    }

    private String createToken(String subject, List<Role> roles) {
        expirationDate = new Date(System.currentTimeMillis() + 24 * HOUR);
        Map<String, Object> claims = Jwts.claims().setSubject(subject);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private boolean jwtIsSigned(String token) {

        return Jwts.parser().isSigned(token);
    }


    private void jwtIsSignedWithGoodKey(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private boolean jwtIsExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public void validateToken(String token) throws UnAuthorizedRequestException {

        if (!jwtIsSigned(token)) {
            throw new UnAuthorizedRequestException(ErrorConstants.TOKEN_UNSIGNED);
        }
        try {
            jwtIsSignedWithGoodKey(token);
        } catch (Exception ex) {
            throw new UnAuthorizedRequestException(ErrorConstants.INVALID_TOKEN_KEY);
        }
        if (jwtIsExpired(token)) {
            throw new UnAuthorizedRequestException(ErrorConstants.TOKEN_EXPIRED);
        }

    }
}
