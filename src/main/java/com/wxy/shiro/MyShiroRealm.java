package com.wxy.shiro;

import com.wxy.model.User;
import com.wxy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wxy
 * @create 2018-02-25 11:21
 * @desc ${DESCRIPTION}
 **/

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;//这里会报错，但是并不会影响

    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("Shiro登录认证启动");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", username);
        //密码进行加密处理  明文为  password+name
        String paw = password + username;
//        String pawDES = MyDES.encryptBasedDes(paw);
//        map.put("pswd", pawDES);

        //从数据库拿到用户信息
        User user = userService.findByName(username);
        // 账号不存在
        if (null == user) {
            throw new UnknownAccountException();
        }

        System.out.println("Shiro登录认证完毕");
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 授权用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Shiro授权启动");
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户角色
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);

        //获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        System.out.println("Shiro授权启动");
        return info;
    }
}