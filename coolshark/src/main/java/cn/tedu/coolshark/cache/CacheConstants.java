package cn.tedu.coolshark.cache;

public class CacheConstants {
    //藉由該類去做本地緩存(cafe' 緩存)

    /**
     * 默认过期时间（配置类中我使用的时间单位是秒，所以这里如 3*60 为3分钟）
     */
    public static final int DEFAULT_EXPIRES = 3 * 60;
    public static final int EXPIRES_5_MIN = 5 * 60;
    public static final int EXPIRES_10_MIN = 10 * 60;
    public static final String CACHE1 = "CACHE1";
    public static final String CACHE2 = "CACHE2";

}
