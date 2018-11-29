package com.uc.training.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.uc.training.common.bean.AccessToken;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 *
 * @author wubc
 * @version V1.0
 * @date 2018年9月26日 上午9:53:25
 */
public final class TokenUtil {
    private TokenUtil() {

    }
    /**
     * 加密密钥
     */
    private static final String SECRET = "432d2eb39bc04f6e7142f3620dba3633";
    /**
     * 默认过期2小时
     */
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    private static final String JWT_ISSUER = "JWT";
    private static final String ID_CLAIM = "id";

    /**
     * 生成token
     *
     * @param id         用户id
     * @param expireTime 过期时间（毫秒ms）
     * @return java.lang.String
     */
    public static String sign(Long id, long expireTime) {
        try {
            Map<String, Object> headerClaims = new HashMap<String, Object>(2);
            headerClaims.put("alg", "HS256");
            headerClaims.put("typ", "JWT");
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + expireTime);
            // 附带username信息
            return JWT.create()
                    .withHeader(headerClaims)
                    .withIssuer(JWT_ISSUER)
                    .withClaim(ID_CLAIM, id)
                    .withIssuedAt(new Date(currentTimeMillis))
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (JWTCreationException exception) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成签名,60min后过期
     *
     * @param id 用户id
     * @return java.lang.String
     */
    public static String sign(Long id) {
        try {
            Map<String, Object> headerClaims = new HashMap<String, Object>(2);
            headerClaims.put("alg", "HS256");
            headerClaims.put("typ", "JWT");
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + EXPIRE_TIME);
            // 附带username信息
            return JWT.create()
                    .withHeader(headerClaims)
                    .withIssuer(JWT_ISSUER)
                    .withClaim(ID_CLAIM, id)
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token
     *
     * @param token token值
     * @return com.ucar.bean.AccessToken
     */
    public static AccessToken verify(String token) {
        AccessToken result = new AccessToken();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(JWT_ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            result.setVerify(Boolean.TRUE);
            result.setId(jwt.getClaim(ID_CLAIM).asLong());
            result.setSignDate(jwt.getIssuedAt());
            result.setExpireDate(jwt.getExpiresAt());
            result.setExpire(jwt.getExpiresAt().compareTo(new Date()) <= 0 ? true : false);
        } catch (TokenExpiredException e) {
            DecodedJWT jwt = JWT.decode(token);
            result.setVerify(Boolean.TRUE);
            result.setExpire(Boolean.TRUE);
            result.setId(jwt.getClaim(ID_CLAIM).asLong());
            result.setSignDate(jwt.getIssuedAt());
            result.setExpireDate(jwt.getExpiresAt());
        } catch (JWTVerificationException e) {
            result.setVerify(Boolean.FALSE);
        } catch (Exception e) {
            result.setVerify(Boolean.FALSE);
        }
        return result;
    }

    /**
     * 验证token
     *
     * @param token token值
     * @return boolean
     */
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验token
     *
     * @param token token值
     * @param id    用户的id
     * @return boolean
     */
    public static boolean verify(String token, Long id) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .withClaim(ID_CLAIM, id)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户id
     *
     * @param token token值
     * @return java.lang.Long
     */
    public static Long getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(ID_CLAIM).asLong();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取颁发时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getIssuedDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取过期时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getExpireDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否过期
     *
     * @param token token值
     * @return boolean
     */
    public static boolean isExpire(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().compareTo(new Date()) <= 0 ? true : false;
        } catch (JWTDecodeException e) {
            return true;
        } catch (Exception e) {
            return true;
        }
    }


    public static void main(String[] args) {
//        String token = sign(1L, 60 * 1000 * 24 * 365);
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKRUVTRSIsImV4cCI6MTUyNjAxMjkyMywidXNlcklkIjoxLCJpYXQiOjE1MjYwMTI4NjN9.NKhWgl_L-TmZCOSOUzTaKQFYFfM7OrjG6O55BQ2Ts9M";
//        System.out.println(token);
//        AccessToken result = verify(token);
//        System.out.println(result.isVerify());
//        System.out.println(result.isExpire());
//        System.out.println(result.getSignDate());
//        System.out.println(result.getExpireDate());
//        System.out.println(result.getId());
//        System.out.println(verify(token, 1L));
//        System.out.println(getId(token));
    }
}
