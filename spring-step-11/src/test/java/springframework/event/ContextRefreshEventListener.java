package springframework.event;

import cn.zgy.springframework.context.ApplicationListener;
import cn.zgy.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
