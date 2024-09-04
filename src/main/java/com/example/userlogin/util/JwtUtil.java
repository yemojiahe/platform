// JWT工具类

package com.example.userlogin.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "your-secret-key";


    //生成token方法
    //Jwts.builder(): 创建一个新的 JWT 构建器。
    //.setSubject(username): 设置 JWT 的主题部分为提供的用户名。
    //.setExpiration(new Date(System.currentTimeMillis() + 86400000)): 设置 JWT 的过期时间为当前时间加上 24 小时（86400000 毫秒）。
    //.signWith(SignatureAlgorithm.HS256, SECRET_KEY): 使用 HS256 签名算法和 SECRET_KEY 对 JWT 进行签名，以保证其完整性和真实性。
    //.compact(): 构建并返回最终的 JWT 字符串。
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}