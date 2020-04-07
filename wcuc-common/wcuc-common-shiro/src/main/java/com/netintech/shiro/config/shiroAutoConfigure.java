package com.netintech.shiro.config;


import com.netintech.shiro.properties.redisProperties;
import com.netintech.shiro.properties.shiroProperties;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableConfigurationProperties({shiroProperties.class, redisProperties.class})
@ConditionalOnBean(ShiroRedisAutoConfigure.class)
public class shiroAutoConfigure {



    @Bean
    @ConditionalOnBean(JedisPool.class)
    @ConditionalOnMissingBean
    public RedisManager redisManager(JedisPool jedisPool){
        RedisManager redisManager = new RedisManager();
        redisManager.setJedisPool(jedisPool);

      /*  redisManager.setHost("192.168.56.3");
        redisManager.setPort(6379);*/
        return redisManager;
    }


    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
    @Bean("redisCacheManager")
    @ConditionalOnMissingBean
    public RedisCacheManager redisCacheManager(RedisManager redisManager , shiroProperties shiroProperties){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        // redisCacheManager.setRedisManager(redisClusterManager());
        redisCacheManager.setRedisManager(redisManager);
        //redis中针对不同用户缓存
        redisCacheManager.setPrincipalIdFieldName(shiroProperties.getPrincipalIdFieldName());
        //用户权限信息缓存时间
        redisCacheManager.setExpire(shiroProperties.getPersExpireTime());
        return redisCacheManager;
    }


    //  begin ：实现 session 共享




    /**
     * sesssion 监听
     * @return
     */
    @Bean("sessionListener")
    @ConditionalOnMissingBean
    public mySessionLisnter sessionListener(){
        mySessionLisnter sessionListener = new mySessionLisnter();
        return sessionListener;
    }


    @Bean
    @ConditionalOnMissingBean
    public SessionIdGenerator sessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 配置保存sessionId的cookie
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
     * 默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid
     * @return
     */
    @Bean("sessionIdCookie")
    @ConditionalOnMissingBean
    public SimpleCookie sessionIdCookie(shiroProperties shiroProperties){
        //这个参数是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie(shiroProperties.getSimple_cookie());
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
       // simpleCookie.setHttpOnly(true);
        //simpleCookie.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(shiroProperties.getCookie_maxAge());
        return simpleCookie;
    }

    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SessionDAO sessionDAO(RedisManager redisManager, redisProperties redisProperties) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        //session在redis中的保存时间,最好大于session会话超时时间
        redisSessionDAO.setExpire(redisProperties.getExpireTime());
        return redisSessionDAO;
    }


    @Bean
    @ConditionalOnMissingBean
    public FormAuthenticationFilter filter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        return formAuthenticationFilter;
    }


//    @Bean
//    @ConditionalOnBean(FormAuthenticationFilter.class)
//    @ConditionalOnMissingBean
//    public FilterRegistrationBean registration(FormAuthenticationFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }




    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    @Bean("sessionManager")
    @ConditionalOnMissingBean
    public SessionManager sessionManager(SessionDAO sessionDAO,
                                         SessionListener sessionListener,
                                         SimpleCookie simpleCookie,
                                         shiroProperties shiroProperties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        //配置监听
          listeners.add(sessionListener);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(simpleCookie);
        sessionManager.setSessionDAO(sessionDAO);
        //sessionManager.setCacheManager(redisCacheManager());

        //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
        sessionManager.setGlobalSessionTimeout(shiroProperties.getGlobalSessionTimeout());
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(shiroProperties.getDeleteInvalidSessions());
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(shiroProperties.getSessionValidationSchedulerEnabled());
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        sessionManager.setSessionValidationInterval(shiroProperties.getSessionValidationInterval());
        //取消url 后面的 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(shiroProperties.getSessionIdUrlRewritingEnabled());
        return sessionManager;

    }


    /**
     * Shiro Realm 继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义的
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public UserRealm userRealm() {
//        UserRealm userRealm = new UserRealm();
//        return userRealm;
//    }




    // ------------------end----------------------

    /**
     * 不指定名字的话，自动创建一个方法名第一个字母小写的bean
     */
    @Bean
    @ConditionalOnMissingBean
    public SecurityManager securityManager(SessionManager sessionManager, AuthorizingRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //  配置redis 缓存
      //  securityManager.setCacheManager(redisCacheManager()); 这个是否有用？
        // 配置自定义session管理
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }



  /*  @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(NAME);
        simpleCookie.setValue(VALUE);
        return simpleCookie;
    }*/
    /**
     * Shiro的Web过滤器Factory 命名:shiroFilter
     * 需要用户自定义
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //Shiro的核心安全接口,这个属性是必须的
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//      /*  Map<String, Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("authc", new AjaxPermissionsAuthorizationFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);*/
//        /*定义shiro过滤链  Map结构
//         * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
//         * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
//         * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
//         */
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//         /* 过滤链定义，从上向下顺序执行，一般将 / ** 放在最为下边:这是一个坑呢，一不小心代码就不好使了;
//          authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
////        filterChainDefinitionMap.put("/", "anon");
////        filterChainDefinitionMap.put("/static/**", "anon");
////        filterChainDefinitionMap.put("/login/auth", "anon");
////        filterChainDefinitionMap.put("/login/logout", "anon");
////        filterChainDefinitionMap.put("/error", "anon");
//        filterChainDefinitionMap.put("/**", "anon");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }



    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     */
//    @Bean(name = "credentialsMatcher")
//    @ConditionalOnMissingBean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //散列的次数，比如散列两次，相当于 md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(1);
//        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return hashedCredentialsMatcher;
//    }

    /**
     * Shiro生命周期处理器
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
