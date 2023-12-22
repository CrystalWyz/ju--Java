package cn.wyz.common.event;

/**
 * @author tieyan
 */
public interface EventPublisher {

    /**
     * 发布一个事件
     *
     * @param event
     */
    void publish(Event event);
}
