package cn.wyz.murdermystery.convert;

import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.response.BlemishDetailPageInfo;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import org.mapstruct.Mapper;

/**
 * @author wangnanxiang
 */
@Mapper(componentModel = "spring")
public interface BeanConvert {

    JuInfo juInfoDTOToJuInfo(JuInfoDTO juInfoDTO);

    JuInfoDTO juInfoToJuInfoDTO(JuInfo juInfo);

    JuInfoPageInfo juInfoTOJuInfoPageInfo(JuInfo juInfo);

    BlemishDetailDTO blemishDetailToBlemishDetailDTO(BlemishDetail blemishDetail);

    BlemishDetail blemishDetailDTOToBlemishDetail(BlemishDetailDTO blemishDetailDTO);

    BlemishDetailPageInfo blemishDetailToBlemishdetailPageInfo(BlemishDetail blemishDetail);
}
