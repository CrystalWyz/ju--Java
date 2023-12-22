package cn.wyz.murdermystery.event;

import cn.wyz.common.event.Event;
import cn.wyz.murdermystery.bean.dto.MurderMysteryDTO;
import cn.wyz.user.holder.SecurityContextHolder;
import lombok.Getter;

/**
 * @author zhouzhitong
 * @since 2023-12-22
 **/
public abstract class BaseMurderMysteryEvent implements Event {

    public static final String PREPARE = "prepare";

    public static final String DISMISS = "dismiss";

    /**
     * 需要处理的剧本杀
     */
    @Getter
    protected final MurderMysteryDTO source;

    /**
     * 处理人
     */
    @Getter
    private final Long userId;

    public BaseMurderMysteryEvent(MurderMysteryDTO source) {
        this(source, SecurityContextHolder.getContext().getUserId());
    }

    public BaseMurderMysteryEvent(MurderMysteryDTO source, Long userId) {
        this.source = source;
        this.userId = userId;
    }

    public abstract String type();

}
