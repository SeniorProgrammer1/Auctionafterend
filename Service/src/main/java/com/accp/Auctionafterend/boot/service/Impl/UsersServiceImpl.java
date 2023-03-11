package com.accp.Auctionafterend.boot.service.Impl;

import com.accp.Auctionafterend.boot.commons.utlis.MD5Util;
import com.accp.Auctionafterend.boot.mapper.UsersMapper;
import com.accp.Auctionafterend.boot.pojo.Users;
import com.accp.Auctionafterend.boot.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> selectByUsAccount(String usAccount) {
        return usersMapper.selectByUsAccount(usAccount);
    }

    @Override
    public Users selectByAccountAndPassWord(String usAccount, String usPwd) {
        QueryWrapper<Users> usersQueryWrapper=new QueryWrapper<>();
        usersQueryWrapper.eq("usAccount",usAccount);
        usersQueryWrapper.eq("usPwd", MD5Util.getMD5(usPwd));
        Users uses =usersMapper.selectOne(usersQueryWrapper);
        return uses;
    }

    @Override
    public Users selectByAccount(String usAccount) {
        QueryWrapper<Users> usersQueryWrapper=new QueryWrapper<>();
        usersQueryWrapper.eq("usAccount",usAccount);
        return usersMapper.selectOne(usersQueryWrapper);
    }
}
