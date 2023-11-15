package cn.wyz.common.advice;

import cn.wyz.common.bean.request.ResponseResult;
import cn.wyz.common.constant.CodeConstant;
import cn.wyz.common.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局默认异常处理
 *
 * @author zhouzhitong
 * @since 2022/05/21
 */
@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice {

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> unException(Exception e) {
        LOGGER.error("", e);
        return ResponseResult.fail(CodeConstant.UN_KNOW_ERROR, e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseResult<Void> userException(BaseRuntimeException e) {
        LOGGER.error("", e);
        return ResponseResult.fail(e.getCode(), e.getMsg());
    }

//    /**
//     * 参数校验异常
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseResult<List<ErrorParamVO>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        LOGGER.error("", e);
//        // 从异常对象中拿到ObjectError对象
//        List<ObjectError> errors = e.getBindingResult().getAllErrors();
//        List<ErrorParamVO> res = Lists.newArrayList();
//        for (ObjectError error : errors) {
//            ErrorParamVO vo = new ErrorParamVO();
//            vo.setCode(error.getCode());
//            vo.setName(error.getObjectName());
//            vo.setMessage(error.getDefaultMessage());
//            res.add(vo);
//        }
//        return ResponseResult.fail(CodeConstant.ILLEGAL_PARAMETER_ERROR, res);
//    }

}