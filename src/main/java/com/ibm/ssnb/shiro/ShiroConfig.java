package com.ibm.ssnb.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * 1 创建shiroFilterFactoryBean
 *
 * 2 登陆安全管理器
 */
@Configuration
public class ShiroConfig {
    //****************视频中代码*************
    // @Bean
    // public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    //     ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    //
    //     // 必须设置SecurityMananer
    //     shiroFilterFactoryBean.setSecurityManager(securityManager);
    //
    //     // 如果不设置默认自动寻找Web工程目录下的"/login.jsp"
    //     // 设置没有登陆跳转的页面
    //     shiroFilterFactoryBean.setLoginUrl("/login");
    //
    //     // 拦截器
    //     Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    //
    //     // 配置不会被拦截的链接顺序
    //     filterChainDefinitionMap.put("/static/**", "anon");
    //     filterChainDefinitionMap.put("/user/login", "anno");
    //     // filterChainDefinitionMap.put("drawImage", "anno"); //验证码
    //
    //     //配置退出过滤器，其中的具体的退出代码Shiro已经替我们实现了
    //     filterChainDefinitionMap.put("/logout", "logout");
    //
    //     // authc需要登陆才能访问
    //     filterChainDefinitionMap.put("/admin/**", "authc");
    //     filterChainDefinitionMap.put("/houtai/**", "authc");
    //
    //     shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    //     return shiroFilterFactoryBean;
    //
    // }

    //****************github中的代码，两者都一样*************
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加内置过滤器
        /**
         * 常用内置过滤器
         *  anon:无需认证（登录）用户可以直接访问（就相当于游客）
         *  authc:必须要认证才能访问
         *  user：使用了remenberMe的功能的用户可以直接无需登录访问（相当于记住登录状态）
         *  perms:必须获取资源权限才能访问
         *  role：必须获取角色授权才能访问
         *  logout:用户登出，这里不用设置控制器，退出后直接跳转到/
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/login","anon");
        //authc需要 登陆才能访问
        filterMap.put("/admin/**", "authc");
        filterMap.put("/houtai/**", "authc");
        filterMap.put("/logout","logout");
        //authc需要 登陆才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //用户登录页面，当用户没登陆时跳转到此页面，没设置时默认跳转到login.jsp
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;

    }

    /**
     * 创建 安全管理器 SecurityManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }//👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇这是第二种写法
   // @Bean
   // public SecurityManager securityManager() {
   //     DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
   //     securityManager.setRealm(getRealm());
   //     return securityManager;
   // }


    @Bean(name = "myRealm")
    public MyRealm getRealm(){
        return new MyRealm();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     * Shiro的生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解（如@RequiresRoles, @RequiresPermissions），需要借助SpringAOP扫描使用Shiro的注解的类，并在必要时进行安全逻辑验证
     * 配置一下两个bean（DefaultAdvisorAutoCreator(可选))和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        // authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager(getRealm()));
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 配置 ShiroDialect 用于thymeleaf 和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}