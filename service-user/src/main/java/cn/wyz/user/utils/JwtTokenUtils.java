package cn.wyz.user.utils;

import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.AppException;
import cn.wyz.common.util.IpUtils;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.context.TokenInfo;
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

    private static final String JWT_KEY_USERNAME = "username";
    private static final String JWT_KEY_IP = "ip";

    private static final String JWT_GENERATE_TIME = "generateTime";

    public static LoginContext getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_PARAMETER);
        if (token != null) {
            TokenInfo claims = parseToken(token);
            String user = claims.getUsername();
            LoginContext loginContext = new LoginContext(user);
            loginContext.setToken(token);
//            LocalDateTime loginTime = (LocalDateTime) claims.get(SecurityConstant.LOGIN_TIME);
//            loginContext.setLoginTime(loginTime);
            // 获取用户当前IP
            String ipAddr = claims.getIp();
            loginContext.setIp(ipAddr);
            return loginContext;
        }
        return null;
    }

    public static String generatorToken(String username) {
        Map<String, String> map = new HashMap<>(4);
        map.put(JWT_KEY_USERNAME, username);
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
        DecodedJWT decodedJWT = JWT
                .require(Algorithm.HMAC256(SIGN))
                .build()
                .verify(token);
        Claim username = decodedJWT.getClaim(JWT_KEY_USERNAME);
        Claim ip = decodedJWT.getClaim(JWT_KEY_IP);
        Claim generateTime = decodedJWT.getClaim(JWT_GENERATE_TIME);
        return TokenInfo.builder()
                .username(username.asString())
                .ip(ip.asString())
                .token(token)
                .tokenCreateTime(decodedJWT.getExpiresAt().getTime())
                .build();
    }

    public static TokenInfo checkToken(String token) {
        TokenInfo tokenContext;
        try {
            tokenContext = JwtTokenUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token sign error");
        } catch (TokenExpiredException e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token time out");
        } catch (Exception e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        if (ObjectUtils.isEmpty(tokenContext)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }
        return tokenContext;
    }

}
