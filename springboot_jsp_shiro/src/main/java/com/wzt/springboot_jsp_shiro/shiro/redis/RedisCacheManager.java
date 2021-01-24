package com.wzt.springboot_jsp_shiro.shiro.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @User:Tao
 * @date:2021/1/20
 * 自定义shiro缓存管理器
 */
public class RedisCacheManager implements CacheManager {
    /**
     *
     * @param cacheName 认证名或者授权名
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("缓存名称：" + cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}
