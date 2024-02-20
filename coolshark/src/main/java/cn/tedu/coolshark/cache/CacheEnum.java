package cn.tedu.coolshark.cache;

public enum CacheEnum {
    //枚舉類 就是固定的對象/物件 就是固定的認知概念ex.一周有幾天 :固定認知7天 這樣

    /**
     * 缓存1
     */
    CACHE1(CacheConstants.CACHE1, CacheConstants.EXPIRES_5_MIN),
    /**
     * 缓存2
     */
    CACHE2(CacheConstants.CACHE2, CacheConstants.EXPIRES_10_MIN),
    ;

    /**
     * 缓存名称
     */
    private final String name;
    /**
     * 过期时间
     */
    private final int expires;

    /**
     * 构造方法 注入 所以不用set注入
     */
    CacheEnum(String name, int expires) {
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public int getExpires() {
        return expires;
    }

}
