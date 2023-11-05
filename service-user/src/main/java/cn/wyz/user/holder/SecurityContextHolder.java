package cn.wyz.user.holder;

import cn.wyz.user.context.LoginContext;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhouzhitong
 * @since 2023/5/16
 */
@Slf4j
public class SecurityContextHolder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final ThreadLocal<LoginContext> CONTEXT_HOLDER = new ThreadLocal<>();


    public static void setContext(LoginContext context) {
        CONTEXT_HOLDER.set(context);
    }

    public static LoginContext getContext() {
        LoginContext loginContext = CONTEXT_HOLDER.get();
        if (loginContext == null) {
            loginContext = new LoginContext(-1L, "anonymous");
        }
        return loginContext;
    }

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }


}
