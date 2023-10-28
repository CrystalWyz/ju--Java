package cn.wyz.user.utils;

import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.util.IpUtils;
import cn.wyz.user.constant.SecurityConstant;
import cn.wyz.user.context.LoginContext;
import cn.wyz.user.exception.UserTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * JWT token 生成
 *
 * @author zzt
 * @since 2021/7/3
 */
@Slf4j
public class JwtTokenUtil {

    public static LoginContext getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_PARAMETER);
        if (token != null) {
            Claims claims = getClaims(token);
            String user = claims.getSubject();
            LoginContext loginContext = new LoginContext(user);
            loginContext.setToken(token);
            LocalDateTime loginTime = (LocalDateTime) claims.get(SecurityConstant.LOGIN_TIME);
            loginContext.setLoginTime(loginTime);
            // 获取用户当前IP
            String ipAddr = claims.get(SecurityConstant.IP, String.class);
            loginContext.setIp(ipAddr);
            return loginContext;
        }
        return null;
    }

    public static String createToken(LoginContext auth) {
        Claims claims = Jwts.claims();
        // 记录用户登录IP
        claims.put(SecurityConstant.IP, IpUtils.getIpAddr());
        // 记录用户登录时间
        claims.put(SecurityConstant.LOGIN_TIME, LocalDateTime.now());
        return createToken(claims, auth.getUsername());
    }

    /**
     * 获取token有效时长
     *
     * @return 有效时长
     */
    public static long getExpiration() {
        return SecurityConstant.TOKEN_EXPIRATION;
    }

    public static String createToken(String username) {
        Claims claims = Jwts.claims();
        // 记录用户登录IP
        claims.put(SecurityConstant.IP, IpUtils.getIpAddr());
        // 记录用户登录时间
        claims.put(SecurityConstant.LOGIN_TIME, LocalDateTime.now());
        return createToken(claims, username);
    }

    public static String createToken(Claims claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET).compact();
    }

    /**
     * 解析token (TODO 错误的token，解析可能抛异常)
     *
     * @param token token
     * @return Claims
     */
    public static Claims getClaims(String token) throws UserTokenExpiredException {
        try {
            return Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            // token 过期
            LOGGER.warn("token parse error {}", e.getMessage());
        } catch (Exception e) {
            // 其他未知异常
            LOGGER.error("", e);
        }
        throw new UserTokenExpiredException(CodeConstant.USER_TOKEN_INVALID_ERROR);
    }

//    public static void main(String[] args) {
//        Claims claims = Jwts.claims();
//        String userName = "admin";
//        claims.put("role", userName);
//        String toke = createToken(claims, userName);
//        Claims claims1 = getClaims(toke);
//    }

}
