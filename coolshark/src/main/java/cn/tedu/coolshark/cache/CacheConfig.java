package cn.tedu.coolshark.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {//這個配置類去做緩存的配置
    /**
     * Caffeine配置说明：
     *   initialCapacity=[integer]: 初始的缓存空间大小
     *   maximumSize=[long]: 缓存的最大条数
     *   expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
     *   refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
     */

    @Bean
    public CacheManager cacheManager(){
        //緩存管理器 將caffeine放入其中
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        //創建緩存 將緩存天加入simpleCacheManager當中
        ArrayList<CaffeineCache> list = new ArrayList<>();
        //利用循環添加枚舉類中自訂的緩存
        for (CacheEnum  cacheEnum : CacheEnum.values()) {
            list.add(new CaffeineCache(cacheEnum.getName(),
                    Caffeine.newBuilder().
                            initialCapacity(50)
                            .maximumSize(1000)
                            .expireAfterWrite(cacheEnum.getExpires(), TimeUnit.SECONDS)
                            .build()));
        }
        simpleCacheManager.setCaches(list);
        return simpleCacheManager;


    }


}
