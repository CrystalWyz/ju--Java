package cn.wyz.murdermystery.convert;

import cn.wyz.murdermystery.bean.BlemishDetail;
import cn.wyz.murdermystery.bean.JuInfo;
import cn.wyz.murdermystery.bean.MurderMystery;
import cn.wyz.murdermystery.bean.bo.MurderMysteryBO;
import cn.wyz.murdermystery.bean.dto.BlemishDetailDTO;
import cn.wyz.murdermystery.bean.dto.JuInfoDTO;
import cn.wyz.murdermystery.bean.request.MurderMysteryPageRequest;
import cn.wyz.murdermystery.bean.response.BlemishDetailPageInfo;
import cn.wyz.murdermystery.bean.response.JuInfoPageInfo;
import cn.wyz.murdermystery.bean.response.MurderMysteryPageResponse;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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

    MurderMystery MurderMysteryPageRequestToMurderMystery(MurderMysteryPageRequest murderMysteryPageRequest);

    PageInfo<MurderMysteryBO> murderMysteryPageToMurderMysteryBOPage(PageInfo<MurderMystery> page);

    MurderMysteryBO murderMysteryToMurderMysteryBO(MurderMystery murderMystery);

    PageInfo<MurderMysteryPageResponse> murderMysteryBOPageToMurderMysteryPageResponsePage(PageInfo<MurderMysteryBO> boPage);

    @Mappings({
            @Mapping(expression = "java(murderMysteryBO.getGirlParticipant() == null ? 0 : murderMysteryBO.getGirlParticipant().size())", target = "girlParticipant"),
            @Mapping(expression = "java(murderMysteryBO.getBoyParticipant() == null ? 0 : murderMysteryBO.getBoyParticipant().size())", target = "boyParticipant")
    })
    MurderMysteryPageResponse murderMysteryToMurderMysteryPageResponse(MurderMysteryBO murderMysteryBO);
}
