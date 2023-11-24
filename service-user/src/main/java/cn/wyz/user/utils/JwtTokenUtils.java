package cn.wyz.user.utils;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;
import cn.wyz.common.util.IpUtils;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.context.TokenInfo;
import cn.wyz.user.constant.Gender;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangnanxiang
 */
public class JwtTokenUtils {

    private static final String SIGN = "CPFwyz@!##";

    private static final String JWT_KEY_USER_ID = "userId";
    private static final String JWT_KEY_USERNAME = "username";
    private static final String JWT_KEY_USER_GENDER = "gender";

    private static final String JWT_KEY_IP = "ip";

    private static final String JWT_GENERATE_TIME = "generateTime";

    public static LoginContext getAuthentication(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (token != null) {
            TokenInfo claims = parseToken(token);
            String user = claims.getUsername();
            Long userId = claims.getUserId();
            Gender gender = claims.getGender();
            LoginContext loginContext = new LoginContext(userId, user);
            loginContext.setToken(token);
            loginContext.setUserId(userId);
            loginContext.setGender(gender);
//            LocalDateTime loginTime = (LocalDateTime) claims.get(SecurityConstant.LOGIN_TIME);
//            loginContext.setLoginTime(loginTime);
            // 获取用户当前IP
            String ipAddr = claims.getIp();
            loginContext.setIp(ipAddr);
            return loginContext;
        }
        return null;
    }

    public static String generatorToken(TokenInfo user) {
        Map<String, String> map = new HashMap<>(4);
        map.put(JWT_KEY_USER_ID, String.valueOf(user.getUserId()));
        map.put(JWT_KEY_USERNAME, user.getUsername());
//        map.put(JWT_KEY_USER_GENDER, String.valueOf(user.getGender().getCode()));
        map.put(JWT_KEY_IP, IpUtils.getIpAddr());
        map.put(JWT_GENERATE_TIME, String.valueOf(System.currentTimeMillis()));

        // token生成
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder
                .withExpiresAt(Instant.now().plusMillis(SecurityConstant.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC256(SIGN));
    }

    public static long getExpiration() {
        return SecurityConstant.TOKEN_EXPIRATION;
    }

    public static TokenInfo parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim username = decodedJWT.getClaim(JWT_KEY_USERNAME);
        Claim userId = decodedJWT.getClaim(JWT_KEY_USER_ID);
        Claim gender = decodedJWT.getClaim(JWT_KEY_USER_GENDER);
        Claim ip = decodedJWT.getClaim(JWT_KEY_IP);
        Claim generateTime = decodedJWT.getClaim(JWT_GENERATE_TIME);
        return TokenInfo.builder()
                .userId(Long.valueOf(userId.asString()))
                .username(username.asString())
                .gender(Gender.of(Integer.parseInt(gender.asString())))
                .ip(ip.asString()).token(token)
                .tokenCreateTime(decodedJWT.getExpiresAt().getTime())
                .build();
    }

    public static TokenInfo checkToken(String token) {
        TokenInfo tokenContext;
        try {
            tokenContext = JwtTokenUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            throw new BaseUserException("token sign error", CodeConstant.USER_TOKEN_INVALID_ERROR);
        } catch (TokenExpiredException e) {
            throw new BaseUserException("token time out", CodeConstant.USER_TOKEN_INVALID_ERROR);
        } catch (Exception e) {
            throw new BaseUserException("token error", CodeConstant.USER_TOKEN_INVALID_ERROR);
        }

        if (ObjectUtils.isEmpty(tokenContext)) {
            throw new BaseUserException("token error", CodeConstant.USER_TOKEN_INVALID_ERROR);
        }
        return tokenContext;
    }

}
