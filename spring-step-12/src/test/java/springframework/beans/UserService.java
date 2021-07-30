package springframework.beans;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.*;
import cn.zgy.springframework.context.ApplicationContext;
import cn.zgy.springframework.context.ApplicationContextAware;

import java.util.Random;

public class UserService implements IUserService{


    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "tom 10001 杭州";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success!";
    }
}
