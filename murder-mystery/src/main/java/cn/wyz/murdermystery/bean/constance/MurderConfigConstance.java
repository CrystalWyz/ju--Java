package cn.wyz.murdermystery.bean.constance;

/**
 * 剧本杀配置类key值说明
 *
 * @author zhouzhitong
 * @since 2023-11-06
 **/
public interface MurderConfigConstance {

    /**
     * 是否启动申请, 默认 不需要申请
     * <p>
     * 数据类型: Boolean
     */
    String ENABLE_APPLY = "enableApply";

    /**
     * 男性人数, 默认 0
     * <p>
     * 数据类型: Integer
     */
    String BOY_PARTICIPANT_NUM = "boyParticipantNum";

    /**
     * 女性人数, 默认 0
     * <p>
     * 数据类型: Integer
     */
    String GIRL_PARTICIPANT_NUM = "girlParticipantNum";

    /**
     * 申请数量, 默认 0
     * <p>
     * 数据类型: Integer
     */
    String APPLY_NUM = "applyNum";

}
