package cn.wyz.user.service.impl;

import cn.wyz.common.service.SystemProvider;
import cn.wyz.user.holder.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author zhouzhitong
 * @since 2023-10-28
 **/
@Service
public class DefaultSystemProvider implements SystemProvider {

    @Override
    public String getCurrentAuditor() {
        return SecurityContextHolder.getContext().getUsername().toLowerCase();
    }

    @Override
    public String getLocale() {
        return null;
    }

    @Override
    public boolean isSupportMultiLanguage() {
        return false;
    }

    @Override
    public String getEnvironmentDefaultLocale() {
        return null;
    }
}
