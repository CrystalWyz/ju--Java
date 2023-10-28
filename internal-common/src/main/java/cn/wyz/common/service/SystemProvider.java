package cn.wyz.common.service;

/**
 * 系统信息提供器
 *
 * @author zhouzhitong
 * @since 2023/5/17
 */
public interface SystemProvider {

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    String getCurrentAuditor();

    /**
     * 获取用户环境语言
     *
     * @return 用户环境语言
     */
    String getLocale();

    /**
     * 是否支持多语言
     *
     * @return 是否支持多语言
     */
    boolean isSupportMultiLanguage();

    /**
     * 获取用户环境默认语言
     *
     * @return 用户环境默认语言
     */
    String getEnvironmentDefaultLocale();

}
