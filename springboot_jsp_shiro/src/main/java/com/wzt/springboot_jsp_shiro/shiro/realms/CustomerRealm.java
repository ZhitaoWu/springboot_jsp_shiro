package com.wzt.springboot_jsp_shiro.shiro.realms;

import com.wzt.springboot_jsp_shiro.entity.Perms;
import com.wzt.springboot_jsp_shiro.entity.User;
import com.wzt.springboot_jsp_shiro.service.UserService;
import com.wzt.springboot_jsp_shiro.utils.ApplicationContextUtils;
import com.wzt.springboot_jsp_shiro.utils.MyByteSource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @User:Tao
 * @date:2021/1/6
 * 自定义Realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证：" + primaryPrincipal);

        // 根据主身份信息获取角色和权限信息
        userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findRolesByUserName(primaryPrincipal);
        // 赋值授权角色
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());

                // 权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    perms.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }

            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("=============");
        String principal = (String) authenticationToken.getPrincipal();
        // 从工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");

        // 根据身份信息查询
        User user = userService.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            // 返回数据库信息
            return new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getPassword(),
                    //ByteSource.Util.bytes(user.getSalt()),
                    new MyByteSource(user.getSalt()),
                    this.getName()
            );

        }// if-end

        return null;
    }
}
