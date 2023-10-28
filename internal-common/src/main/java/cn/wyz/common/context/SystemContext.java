package cn.wyz.common.context;

import cn.wyz.common.service.SystemProvider;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 系统参数, 获取系统的语言环境, 当前操作用户等等信息...
 *
 * @author zhouzhitong
 * @version 1.0
 * @since 2022/6/18
 */
public class SystemContext {

    /**
     * 中文
     */
    public static final String LOCALE_ZH_CN = "zh_CN";
    /**
     * 英文
     */
    public static final String LOCALE_EN_US = "en_US";

    /**
     * 韩文
     */
    public static final String LOCALE_KOR_ROK = "ko_KR";


    /**
     * 系统默认语言支持: zh_CN
     */
    public static final String DEFAULT_LOCALE = LOCALE_ZH_CN;

    /**
     * 系统默认操作用户: system
     */
    public static final String DEFAULT_OPERATOR = "system";

    @Setter
    private static SystemProvider systemProvider;


    /**
     * 系统是否启动
     */
    private static volatile Boolean isRunning = false;

    /**
     * 获取当前操作用户
     *
     * @return
     */
    public static String getUsername() {
        if (systemProvider != null) {
            return systemProvider.getCurrentAuditor();
        }
        return DEFAULT_LOCALE;
    }

    /**
     * 获取当前操作语言
     *
     * @return
     */
    public static String getLocale() {
        if (systemProvider != null) {
            return systemProvider.getLocale();
        }
        return getDefaultLocale();
    }

    public static boolean isDefaultLocale() {
        return StringUtils.equals(getLocale(), getLocale());
    }

    public static String getDefaultLocale() {
        if (systemProvider != null) {
            return systemProvider.getEnvironmentDefaultLocale();
        }
        return DEFAULT_LOCALE;
    }

    public static void finish() {
        isRunning = true;
    }

    public static Boolean isRunning() {
        return isRunning;
    }

}
