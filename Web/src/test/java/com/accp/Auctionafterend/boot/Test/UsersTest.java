package com.accp.Auctionafterend.boot.Test;

import com.accp.Auctionafterend.boot.commons.utlis.MD5Util;
import com.accp.Auctionafterend.boot.commons.utlis.util;
import com.accp.Auctionafterend.boot.communal.GetSession;
import com.accp.Auctionafterend.boot.mapper.BidrecordMapper;
import com.accp.Auctionafterend.boot.mapper.UsersMapper;
import com.accp.Auctionafterend.boot.pojo.Auctionitems;
import com.accp.Auctionafterend.boot.pojo.Users;
import com.accp.Auctionafterend.boot.service.IAuctionitemsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UsersTest {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    IAuctionitemsService iAuctionitemsService;

    @Autowired
    BidrecordMapper bidrecordMapper;

    @Test
    void test(){
        Page<Auctionitems> page=new Page<>(1,2);
        QueryWrapper<Auctionitems> auctionitemsQueryWrapper=new QueryWrapper<>();
        auctionitemsQueryWrapper.ge("aiStartDate", util.GetsCheCurrentSystemTime(0));
    }

    @Test
    void page(){
        QueryWrapper<Auctionitems> ac=new QueryWrapper<>();
        iAuctionitemsService.list(ac);
    }
}
