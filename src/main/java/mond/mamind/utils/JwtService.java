package mond.mamind.utils;


import io.jsonwebtoken.*;
import mond.mamind.config.BaseException;
import mond.mamind.config.secret.Secret;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static mond.mamind.config.BaseResponseStatus.*;

@Component
public class JwtService {

    /*
    JWT 생성
    @param userIdx
    @return String
     */
    public String createJwt(Long userIdx) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1000*10))//1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    /*
    클라이언트로 부터 온 Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }

    /*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     */
    public Long getUserId() throws BaseException {
        //1. JWT 추출
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0) {
            throw new BaseException(EMPTY_JWT);
        }
        try {
            accessToken = accessToken.split("Bearer ")[1];
        } catch (Exception e) {
            throw new BaseException(REQUEST_JWT_ERROR);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(INVALID_JWT);
        }

        // 3. userIdx 추출
        return claims.getBody().get("userId", Long.class);  // jwt 에서 userIdx를 추출합니다.
    }
}
