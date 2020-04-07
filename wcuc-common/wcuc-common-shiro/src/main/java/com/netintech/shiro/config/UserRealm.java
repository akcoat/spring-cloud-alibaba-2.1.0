package com.netintech.shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class UserRealm extends AuthorizingRealm {




    /**
     * 获取用户的 所有权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       // SecurityUtils
//        ThreadLocal threadLocal = new ThreadLocal();
//        InheritableThreadLocal
//        Subject subject = SecurityUtils.getSubject();
//        subject.isAuthenticated()
//        JSONObject user = (JSONObject)principalCollection.getPrimaryPrincipal();
//        log.info("用户姓名为："+user.getString("username"));
//        JSONObject userPermission = permissionService.getUserPermission(user.getString("username"));
//        SimpleAuthorizationInfo authorizationInfo =new SimpleAuthorizationInfo();
//        //  根据 userId  and  userName 从redis 中获取权限列表
//      //  authorizationInfo.addRoles();
//        authorizationInfo.addStringPermissions((Collection<String>) userPermission.get("permissionList"));
        return null;
    }


    // 验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = (String) authenticationToken.getPrincipal();
//         String password= new String((char[]) authenticationToken.getCredentials());
//        JSONObject user = loginService.getUser(username, password);
//        if (user == null) {
//            //没找到帐号
//            throw new UnknownAccountException();
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user,
//                user.getString("password"),
//                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
//                getName()
//        );
//        //将用户信息放入session中
//        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
//        return authenticationInfo;
        return null;
    }
}
