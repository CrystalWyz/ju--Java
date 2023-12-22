package cn.wyz.murdermystery.event;

import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;

/**
 * @author zhouzhitong
 * @since 2023-12-22
 **/
public class MurderMysteryDismissEvent extends BaseMurderMysteryEvent {

    public MurderMysteryDismissEvent(MurderMysteryDTO source) {
        super(source);
    }

    public MurderMysteryDismissEvent(MurderMysteryDTO source, Long userId) {
        super(source, userId);
    }

    @Override
    public String type() {
        return DISMISS;
    }

}
