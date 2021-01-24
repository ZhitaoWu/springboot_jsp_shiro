package com.wzt.springboot_jsp_shiro.config;

import com.wzt.springboot_jsp_shiro.shiro.realms.CustomerRealm;
import com.wzt.springboot_jsp_shiro.shiro.redis.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @User:Tao
 * @date:2021/1/6
 * 用于整个shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {

    // 1.创建shiroFilter
    // 负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 配置系统受限资源
        // 配置系统公共资源
        //Map<String,String> map = new HashMap<String,String>();

        // 必须为LinkedHashMap 否则anon不生效
        // anon 要在 authc 之前配置
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/login","anon");
        map.put("/register.jsp","anon");
        map.put("/user/register","anon");
        map.put("/user/**","anon"); //anon白名单
        map.put("/user/getImage","anon");
        map.put("/**","authc"); //authc请求这个资源需要认证和授权

        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    // 2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecutityManager(@Qualifier("myBean") Realm realm) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 3.创建自定义realm
    @Bean(name = "myBean")
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();

        // 设置hashed凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 开启缓存管理器
        //customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);//开启全局缓存

        customerRealm.setAuthorizationCachingEnabled(true);//授权
        customerRealm.setAuthorizationCacheName("authorizationCache");

        customerRealm.setAuthenticationCachingEnabled(true);//认证
        customerRealm.setAuthenticationCacheName("authenticationCache");



        return customerRealm;
    }


}
