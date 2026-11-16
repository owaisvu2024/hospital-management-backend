package com.hospital.demo.My_Hospital_Mangment_App.Security;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtUtil {
    private SecretKey secretKey= Keys.hmacShaKeyFor(
            "uieuiebfd3ifuy3vjhewbdui3fhech3uiyduqevwyutdfiuu32vehge".getBytes()
    );
    public String generateToken(String userName, Set<Role> role){
        List<String> roles=new ArrayList<>();
        for(Role role1:role){
            roles.add("ROLE_"+role1.name());
        }
        return Jwts.builder()
                .subject(userName)
                .claim("roles",roles)
                .issuedAt(new Date())
                .signWith(secretKey)
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .compact();
    }
    boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public String getUserName(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    public List<String > getRole(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles",List.class);


    }



}
