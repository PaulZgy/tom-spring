package springframework.beans;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<String, String>();

    public void initDataMethod() {
        System.out.println("执行：init-method");
        hashMap.put("10001", "tom");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod() {
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
