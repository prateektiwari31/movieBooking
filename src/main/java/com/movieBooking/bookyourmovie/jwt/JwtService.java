package com.movieBooking.bookyourmovie.jwt;

import com.movieBooking.bookyourmovie.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value(("${jwt.expiration}"))
    private Long jwtExpiration;


    public String extractUsername(String jwtToken) {
        return extratClaim(jwtToken, Claims::getSubject);
    }

    private  <T>T extratClaim(String jwtToken, Function<Claims,T> claimResolver)
    {
        final Claims claims = extratAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }
    private Claims extratAllClaims(String jwtToken)
    {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(jwtToken).getPayload();
    }
    public SecretKey getSignInKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails)
    {
        return  Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }


    public boolean isTokenValid(String jwtToken, UserDetails userdetails) {
        final String username=extractUsername(jwtToken);
        return (userdetails.getUsername().equals(username) && !isTokenExpired(jwtToken));
    }

    private  boolean isTokenExpired(String jwtToken)
    {
        return extractExpiration(jwtToken).before(new Date());
    }
    private Date extractExpiration(String jwtToken)
    {
        return extratClaim(jwtToken,Claims::getExpiration);
    }
}
