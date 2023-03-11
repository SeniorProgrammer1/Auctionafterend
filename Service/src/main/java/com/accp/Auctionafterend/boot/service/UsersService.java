package com.accp.Auctionafterend.boot.service;

import com.accp.Auctionafterend.boot.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService extends IService<Users> {

    /**
     * 按用户账户查询用户信息
     * @param usAccount
     * @return
     */
    List<Users> selectByUsAccount(@Param("usAccount") String usAccount);

    Users selectByAccountAndPassWord(String usAccount, String usPwd);

    Users selectByAccount(String usAccount);
}
