package springframework.event;

import cn.zgy.springframework.context.ApplicationListener;
import cn.zgy.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件: " + this.getClass().getName());
    }
}
