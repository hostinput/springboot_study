package com.example.springboot.conf;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);        //设置安全管理器
        Map<String, String> filterMap = new HashMap<String, String>();
        filterMap.put("/user/register", "anon");//注册不拦截
        filterMap.put("/user/toRegister", "anon");//跳转到注册不拦截
        filterMap.put("/user/toLogin", "anon");//跳转到登录不拦截
        filterMap.put("/user/login", "anon");//登录不拦截
        filterMap.put("/user/toNouth", "anon");//跳转到无权限不拦截
        filterMap.put("/user//toError", "anon");//跳转到错误页面不拦截
        filterMap.put("/css/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/html/**", "anon");
        filterMap.put("/logout", "logout");//配置退出 过滤器,其中的具体的退出代码Shiro已经实现
        filterMap.put("/**", "authc");//过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");// 	如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //shiroFilterFactoryBean.setSuccessUrl("/user/toIndex"); //  登录成功后要跳转的链接
        //shiroFilterFactoryBean.setUnauthorizedUrl("/user/toNouth");//未授权界面;
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;

    }

    //权限管理，配置主要是Realm的管理认证
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);        //设置realm.
        /*
         * 此处一定要配置缓存 ehcache否则每次都会从数据库查询doGetAuthorizationInfo（）走这个方法
         * */
        securityManager.setCacheManager(ehCacheManager());
        // 自定义缓存实现 使用rediss
        // securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        // securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    //将自己的验证方式加入容器
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm() {//参数：@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hCM
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //myShiroRealm.setCredentialsMatcher(hCM);
        return myShiroRealm;
    }

    //凭证匹配器（由于密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
    //@Bean(name="hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
