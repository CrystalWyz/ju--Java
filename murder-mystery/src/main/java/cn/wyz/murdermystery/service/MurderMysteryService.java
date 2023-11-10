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

    /**
     * 聚--剧本杀分页查询
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageInfo<MurderMysteryBO> murderMysteryPage(PageVM<MurderMysteryPageRequest> pageRequest);

    /**
     * 聚--剧本杀创建
     * @param murderMysteryBO 新实体信息
     * @return id
     */
    Long murderMysteryCreate(MurderMysteryBO murderMysteryBO);
}
