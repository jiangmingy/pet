package com.ttm.pet.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    static final String SECRET = "CZX123jwt456bycx";

    public static String generateToken(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //you can put any data in the map
        map.put("userId", id);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))// 1 hour
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60))// 1 min
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }

    public static void validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Invalid Token. "+e.getMessage());
        }
    }
}
