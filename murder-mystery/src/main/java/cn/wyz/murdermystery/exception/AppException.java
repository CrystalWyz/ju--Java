package cn.wyz.murdermystery.exception;

/**
 * @author wangnanxiang
 */
public class AppException extends RuntimeException {

    private AppException() {

    }

    public AppException(String message) {
        super(message);
    }
}
