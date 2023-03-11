package com.accp.Auctionafterend.boot.controller;

import com.accp.Auctionafterend.boot.commons.commons.Contants;
import com.accp.Auctionafterend.boot.commons.domain.ReturnObject;
import com.accp.Auctionafterend.boot.commons.utlis.MD5Util;
import com.accp.Auctionafterend.boot.commons.utlis.UUIDUtils;
import com.accp.Auctionafterend.boot.commons.utlis.util;
import com.accp.Auctionafterend.boot.communal.GetSession;
import com.accp.Auctionafterend.boot.mapper.UsersMapper;
import com.accp.Auctionafterend.boot.pojo.Users;
import com.accp.Auctionafterend.boot.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boot/users")
//@RequestMapping("myController")
@Api(value = "用户管理")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    UsersMapper usersMapper;

//    @GetMapping("userLogin")
//    public String userLogin(String usAccount,String usPwd){
//        System.out.println(usAccount+"----"+usPwd);
//        //1获取subjeck对象
//        Subject subject = SecurityUtils.getSubject();
//        //2封装请求数据到token
//        AuthenticationToken token = new UsernamePasswordToken(usAccount,MD5Util.getMD5(usPwd));
//        //3调用方法进行登入认证
//        try {
//            subject.login(token);
//            return "登入成功";
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            return "登入失败";
//        }
//    }

    @PostMapping
    @ApiOperation(value = "根据账户密码查询用户",httpMethod = "POST")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "usAccount",value = "账户",required = true,paramType = "String"),
            @ApiImplicitParam(name = "usPwd",value = "密码",required = true,paramType = "String"),
            @ApiImplicitParam(name = "isEmp",value = "记住密码",required = false,paramType = "String")
    })
    public Object getLoging(String usAccount, String usPwd,String isEmp, HttpSession session){
        ReturnObject<Users> ro=new ReturnObject<>();
        System.out.println(usAccount+"--"+usPwd);
        Users users = usersService.selectByAccountAndPassWord(usAccount,usPwd);
        if(users!=null){
            session.setAttribute(Contants.SESSION_USERS,users);
            session.setMaxInactiveInterval(60 * 60);
            ro.setRetData(users);
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            ro.setMessage("登入成功");
            ro.setRetData(users);
            if ("true".equals(isEmp)) {
                Cookie userAccount=new Cookie("usAccount",usAccount);
                userAccount.setMaxAge(600);
                Cookie userPwd=new Cookie("usPwd",usPwd);
                userPwd.setMaxAge(600);
            }else{
                Cookie userAccount=new Cookie("usAccount","");
                userAccount.setMaxAge(0);
                Cookie userPwd=new Cookie("usPwd","");
                userPwd.setMaxAge(0);
            }
        }else{
            ro.setRetData(null);
            ro.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            ro.setMessage("账户或密码错误，请重新输入！");
        }
        return ro;
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    @ApiImplicitParam(name = "users",value = "添加 实体类参数",required = true,paramType = "object")
    public Object getInsertUser(@RequestBody Users users){
        ReturnObject<Users> ro=new ReturnObject<>();
        System.out.println(users);
        users.setUsId(UUIDUtils.getUUID());
        users.setUsPrice(0f);
        users.setUsPwd(MD5Util.getMD5(users.getUsPwd()));
        boolean save = usersService.save(users);
        ro.setRetData(null);
        if(save){
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }
        ro.setMessage(util.whetherOrNot(save,1));
        return ro;
    }

    @GetMapping("/getSelectByUsAccount")
    @ApiOperation(value = "按用户账号查询用户信息")
    public Object getSelectByUsAccount(String usAccount){
        ReturnObject<Users> ro=new ReturnObject<>();
        List<Users> users = usersService.selectByUsAccount(usAccount);
        if(users.size()>0){
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            ro.setMessage("账号已存在，请更换账号！");
            ro.setRetData(null);
        }else{
            ro.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            ro.setMessage("已通过");
        }
        return ro;
    }

    @GetMapping("/invalidate")
    @ApiOperation(value = "销毁session对象")
    public Object getInvalidate(HttpSession session){
        ReturnObject<Users> ro=new ReturnObject<>();
        session.removeAttribute(Contants.SESSION_USERS);
        ro.setRetData(null);
        ro.setMessage("退出成功！");
        return ro;
    }

    @GetMapping("/session")
    @ApiOperation(value = "获取session对象")
    public Object getSession(){
        ReturnObject<Users> ro=new ReturnObject<>();
        ro.setRetData((Users) GetSession.Session());
        ro.setMessage("获取成功！");
        return ro;
    }
}
