//package com.accp.Auctionafterend.boot.realm;
//
//import com.accp.Auctionafterend.boot.pojo.Users;
//import com.accp.Auctionafterend.boot.service.UsersService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UsersService usersService;
//
//    //自定义授权方法
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//    //自定义登入认证方法
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //1获取用户是否信息
//        String name = authenticationToken.getPrincipal().toString();
//        //2调用业务层获取用户信息（数据库）
//        Users users=usersService.selectByAccount(name);
//        //3非空判断，将数据封装返回
//        if(users != null){
//            AuthenticationInfo info = new SimpleAuthenticationInfo(
//                    authenticationToken.getPrincipal(),
//                    users.getUsPwd(),
//                    ByteSource.Util.bytes("salt"),
//                    authenticationToken.getPrincipal().toString()
//            );
//            return info;
//        }
//        return null;
//    }
//}
