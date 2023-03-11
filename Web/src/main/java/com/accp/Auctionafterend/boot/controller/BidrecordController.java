package com.accp.Auctionafterend.boot.controller;


import com.accp.Auctionafterend.boot.commons.commons.Contants;
import com.accp.Auctionafterend.boot.commons.domain.ReturnObject;
import com.accp.Auctionafterend.boot.commons.utlis.UUIDUtils;
import com.accp.Auctionafterend.boot.commons.utlis.util;
import com.accp.Auctionafterend.boot.communal.GetSession;
import com.accp.Auctionafterend.boot.pojo.Bidrecord;
import com.accp.Auctionafterend.boot.pojo.Users;
import com.accp.Auctionafterend.boot.service.IBidrecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@Slf4j
@RestController
@Api(value = "竞拍记录管理")
@RequestMapping("/boot/bidrecord")
public class BidrecordController {

    @Autowired
    private IBidrecordService iBidrecordService;

    @PostMapping
    @ApiOperation(value = "添加竞拍记录",httpMethod = "POST")
    @ApiImplicitParam(name = "bidrecord",value = "bidrecord添加对象",required = true,paramType = "object")
    public Object getSave(@RequestBody Bidrecord bidrecord,HttpSession session){
        ReturnObject<Object> ro=new ReturnObject<>();
            System.out.println(bidrecord);
//            Users users= (Users) session.getAttribute(Contants.SESSION_USERS);
//            System.out.println(users+"=============");
            bidrecord.setBrId(UUIDUtils.getUUID());
//            bidrecord.setBrAiId(users.getUsId());
//            bidrecord.setBrBidder(users.getUsAccount());
            bidrecord.setBrPartakeDate(util.GetsCheCurrentSystemTime(0));
            iBidrecordService.saveBidrecord(bidrecord);
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            ro.setMessage("竞拍成功！");
            return ro;
    }

    @GetMapping
    @ApiOperation(value = "按竞拍id查询竞拍记录",httpMethod = "GET")
    public Object selectByBrId(String brId){
        ReturnObject<Object> ro=new ReturnObject<>();
        QueryWrapper<Bidrecord> bw=new QueryWrapper<>();
        bw.eq("brAiId",brId);
        List<Bidrecord> list = iBidrecordService.list(bw);
        if(list.size()>0){
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            ro.setMessage("已被竞拍，不能进行删除！");
        }else ro.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
        return ro;
    }
}
