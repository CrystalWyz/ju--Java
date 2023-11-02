package cn.wyz.murdermystery.service;

import cn.wyz.common.bean.request.PageVM;
import cn.wyz.murdermystery.bean.bo.MurderMysteryBO;
import cn.wyz.murdermystery.bean.request.MurderMysteryPageRequest;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.bean.response.MurderMysteryPageResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author wyz
 */
public interface MurderMysteryService {
    PageInfo<MurderMysteryBO> murderMysteryPage(PageVM<MurderMysteryPageRequest> pageRequest);
}
