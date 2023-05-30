package cn.wyz.murdermystery.service;

import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.request.PageVM;
import cn.wyz.murdermystery.bean.response.BlemishDetailPageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wyzZzz
 * @since 2023-03-12 10:52:41
 */
public interface BlemishDetailService {

    /**
     * 污点分页查询
     * @param pageRequest 分页请求信息
     * @return 分页查询结果
     */
    List<BlemishDetailPageInfo> blemishDetailPage(PageVM<BlemishDetailDTO> pageRequest);

    /**
     * 污点详情
     * @param blemishDetailId 污点id
     * @return 污点详情
     */
    BlemishDetail blemishDetailDetail(Long blemishDetailId);

    /**
     * 创建污点信息
     * @param blemishDetail 污点详情
     * @return 污点id
     */
    Long createBlemishDetail(BlemishDetail blemishDetail);

    /**
     * 删除污点信息
     * @param blemishDetailId 污点id
     * @return 删除结果
     */
    int deleteBlemishDetail(Long blemishDetailId);
}
