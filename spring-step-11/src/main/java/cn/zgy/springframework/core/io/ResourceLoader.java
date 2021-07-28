package cn.zgy.springframework.core.io;

public interface ResourceLoader {
    /**
     * 从类路径加载的伪URL前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";
    Resource getResource(String location);
}
