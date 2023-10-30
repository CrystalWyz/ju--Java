package cn.wyz.common.util;

import cn.wyz.common.bean.dto.TokenDTO;
import cn.wyz.common.constant.CommonStatusEnum;
import cn.wyz.common.exception.AppException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangnanxiang
 */
@Deprecated
public class JwtUtils {

    private static final String SIGN = "CPFwyz@!##";

    private static final String JWT_KEY_PHONE = "phone";

    private static final String JWT_KEY_IDENTITY = "identity";

    private static final String JWT_TOKEN_TYPE = "tokenType";

    private static final String JWT_GENERATE_TIME = "generateTime";

    public static String generatorToken(TokenDTO tokenDTO, String tokenType) {
        Map<String, String> map = new HashMap<>(4);
        map.put(JWT_KEY_PHONE, tokenDTO.getPhone());
        map.put(JWT_KEY_IDENTITY, tokenDTO.getIdentity());
        map.put(JWT_TOKEN_TYPE, tokenType);
        map.put(JWT_GENERATE_TIME, String.valueOf(System.nanoTime()));

        // token生成
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    public static TokenDTO parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim phone = decodedJWT.getClaim(JWT_KEY_PHONE);
        Claim identity = decodedJWT.getClaim(JWT_KEY_IDENTITY);

        return TokenDTO.builder().phone(phone.asString()).identity(identity.asString()).build();
    }

    public static TokenDTO checkToken(String token) {
        TokenDTO tokenDTO;
        try {
            tokenDTO = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token sign error");
        } catch (TokenExpiredException e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token time out");
        } catch (Exception e) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }

        if (ObjectUtils.isEmpty(tokenDTO)) {
            throw new AppException(CommonStatusEnum.FAIL.getCode(), "token error");
        }
        return tokenDTO;
    }

}
