package springframework.beans;

import cn.zgy.springframework.beans.BeansException;
import cn.zgy.springframework.beans.factory.*;
import cn.zgy.springframework.beans.factory.annotation.Autowired;
import cn.zgy.springframework.beans.factory.annotation.Value;
import cn.zgy.springframework.context.ApplicationContext;
import cn.zgy.springframework.context.ApplicationContextAware;
import cn.zgy.springframework.stereotype.Component;

import java.util.Random;

@Component("userService")
public class UserService implements IUserService{

    private String token;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "tom, 10001, 美团， "  + token;
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

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
