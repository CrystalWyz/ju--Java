package cn.wyz.user.advice;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseUserException;
import cn.wyz.user.exception.UserDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局默认异常处理
 *
 * @author zhouzhitong
 * @since 2022/05/21
 */
@Order(value = 0)
@RestControllerAdvice
@Slf4j
public class UserControllerAdvice {

    /**
     * 异常处理：登录失败
     */
    @ExceptionHandler(BaseUserException.class)
    public ResponseEntity<ResponseResult<?>> userException(BaseUserException e) {
        LOGGER.error("", e);
        ResponseResult<Void> res = ResponseResult.fail(e.code().code(), e.getMsg());
        return this.buildResponseEntity(res, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserDeniedException.class)
    public ResponseEntity<ResponseResult<?>> accessDeniedException(UserDeniedException e) {
        LOGGER.error("", e);
        ResponseResult<Void> res = ResponseResult.fail(CodeConstant.USER_TOKEN_DENIED_PERMISSION);
        return this.buildResponseEntity(res, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ResponseResult<?>> buildResponseEntity(ResponseResult<?> ResponseResult, HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ResponseResult, httpHeaders, status);
    }


}