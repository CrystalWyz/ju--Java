package cn.wyz.murdermystery.event;

import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;

/**
 * @author zhouzhitong
 * @since 2023-12-22
 **/
public class MurderMysteryPrepareEvent extends BaseMurderMysteryEvent {

    public MurderMysteryPrepareEvent(MurderMysteryDTO source) {
        super(source);
    }

    public MurderMysteryPrepareEvent(MurderMysteryDTO source, Long userId) {
        super(source, userId);
    }

    @Override
    public String type() {
        return PREPARE;
    }

}
