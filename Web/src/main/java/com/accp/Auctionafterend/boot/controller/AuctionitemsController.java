package com.accp.Auctionafterend.boot.controller;


import com.accp.Auctionafterend.boot.commons.commons.Contants;
import com.accp.Auctionafterend.boot.commons.domain.ReturnObject;
import com.accp.Auctionafterend.boot.commons.utlis.UUIDUtils;
import com.accp.Auctionafterend.boot.commons.utlis.util;
import com.accp.Auctionafterend.boot.communal.GetSession;
import com.accp.Auctionafterend.boot.conditions.AuctionConditions;
import com.accp.Auctionafterend.boot.pojo.Auctionitems;
import com.accp.Auctionafterend.boot.pojo.Users;
import com.accp.Auctionafterend.boot.pojo.vo.AuctionitemsVo;
import com.accp.Auctionafterend.boot.service.IAuctionitemsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "竞拍物品管理")
@CrossOrigin
@RequestMapping("/boot/auctionitems")
public class AuctionitemsController {

    @Autowired
    private IAuctionitemsService iAuctionitemsService;

    @PostMapping
    @ApiOperation(value = "添加竞拍物品",httpMethod = "POST")
    @ApiImplicitParam(name = "Auctionitems",value = "Auctionitems添加对象",required = true,paramType = "object")
    public Object getInsert(@RequestBody Auctionitems auctionitems){
        ReturnObject<Object> ro=new ReturnObject<>();
        Users users= (Users) GetSession.Session();
        auctionitems.setAiId(UUIDUtils.getUUID());
        auctionitems.setAiUsId(users.getUsId());
        auctionitems.setAiUsAccount(users.getUsAccount());
        boolean save = iAuctionitemsService.save(auctionitems);
        if(save) ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        else ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        ro.setRetData(null);
        ro.setMessage(util.whetherOrNot(save,1));
        return ro;
    }

    @GetMapping
    @ApiOperation(value = "查询所开始的竞拍物品",httpMethod = "GET")
    @ApiImplicitParam(name = "ac",value = "ac条件类",required = true,paramType = "object")
    public Object getSeleteByAuctionConditions(AuctionConditions ac){
        ReturnObject<Object> ro=new ReturnObject<>();
        Page<Auctionitems> page=new Page<>(ac.getPageNum(),2);
        QueryWrapper<Auctionitems> aw=new QueryWrapper<>();
        aw.orderByAsc("aiStartDate");
        if(util.isString(ac.getAiName())) aw.like("aiName",ac.getAiName());

        if(util.isString(ac.getAiDescribe())) aw.like("aiDescribe",ac.getAiDescribe());

        if(ac.getAiPrice()>0) aw.ge("aiPrice",ac.getAiPrice());

        if(util.isString(ac.getAiFinishDate()) && util.isString(ac.getAiStartDate())){
            aw.ge("aiStartDate",ac.getAiStartDate()).le("aiFinishDate",ac.getAiFinishDate());
        }else aw.ge("aiStartDate",util.GetsCheCurrentSystemTime(0));
        Page<Auctionitems> Auctionitems = iAuctionitemsService.AuctionitemsPage(page, aw);
        if(Auctionitems!=null){
            ro.setRetData(Auctionitems);
            ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }else ro.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
        return ro;
    }

    @PostMapping("/getSelectByAiId/{aiId}")
    @ApiOperation(value = "查询物品详细信息",notes = "按id查询",httpMethod = "POST")
    @ApiImplicitParam(name = "aiId",value = "拍卖物品Id",required = true,paramType = "String")
    public Object getSelectByAiId(@PathVariable("aiId") String aiId){
        ReturnObject<Object> ro=new ReturnObject<>();
        ro.setRetData(iAuctionitemsService.selectByAiId(aiId).get(0));
        ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        return ro;
    }

    @GetMapping("/SeleteMyItems")
    @ApiOperation(value = "查询当前账号拍卖的物品",httpMethod = "GET")
    @ApiImplicitParam(name = "pageNum",value = "当前页",required = true,paramType = "Integer")
    public Object getSeleteMyItems(AuctionConditions ac) {
        ReturnObject<Object> ro=new ReturnObject<>();
        System.out.println(ac);
        Users users = (Users) GetSession.Session();
        QueryWrapper<Auctionitems> aw = new QueryWrapper<>();
        aw.eq("aiUsId", users.getUsId());
        if(util.isString(ac.getAiName())) aw.like("aiName",ac.getAiName());
        if(util.isString(ac.getAiDescribe())) aw.like("aiDescribe",ac.getAiDescribe());
        if(ac.getAiPrice()>0) aw.eq("aiPrice",ac.getAiPrice());
        if(util.isString(ac.getAiFinishDate()) && util.isString(ac.getAiStartDate())){
            aw.ge("aiStartDate",ac.getAiStartDate()).le("aiFinishDate",ac.getAiFinishDate());
        }
        aw.orderByAsc("aiStartDate");
        Page<Auctionitems> page = new Page<>(ac.getPageNum(), 2);
        Page<Auctionitems> Auctionitems = iAuctionitemsService.AuctionitemsPage(page, aw);
        ro.setRetData(Auctionitems);
        ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        return ro;
    }

    @PutMapping
    @ApiOperation(value = "修改物品信息")
    @ApiImplicitParam(name = "auctionitems",value = "添加对象参数",required = true,paramType = "object")
    public Object getUpdateById(@RequestBody Auctionitems auctionitems){
        System.out.println(auctionitems);
        ReturnObject<Object> ro=new ReturnObject<>();
        iAuctionitemsService.updateAiId(auctionitems);
        ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        ro.setMessage("修改成功!");
        return ro;
    }

    @DeleteMapping
    @ApiOperation(value = "根据物品ID删除物品")
    @ApiImplicitParam(name = "aiId",value = "删除参数",required = true,paramType = "String")
    public Object getDeleteById(String aiId){
        ReturnObject<Object> ro=new ReturnObject<>();
        log.info("aiId:{}",aiId);
        iAuctionitemsService.deleteByAiId(aiId);
        ro.setMessage("删除成功");
        ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        return ro;
    }

    @GetMapping("/getStartEnd/{isEmp}/{PageNum}")
    @ApiOperation(value = "查询开始拍卖的物品及结束的物品",httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "isEmp",value = "判断查询开始物品或结束物品",required = true,paramType = "String"),
            @ApiImplicitParam(name = "PageNum",value = "分页查询的当前页",required = true,paramType = "String")
    })
    public Object getStartEnd(@PathVariable("isEmp") String isEmp,@PathVariable("PageNum") Integer PageNum) {
        log.info(isEmp);
        ReturnObject<Object> ro=new ReturnObject<>();
        Users users = (Users) GetSession.Session();
        PageInfo<AuctionitemsVo> auctionitemsVoPageInfo =
                iAuctionitemsService.selectByIdAuctionitemsVoPage(util.GetsCheCurrentSystemTime(0),
                        users.getUsId(), isEmp, PageNum);
//        List<AuctionitemsVo> auctionitemsVos = iAuctionitemsService.selectByIdAuctionitemsVo(util.GetsCheCurrentSystemTime(0), users.getUsId(), isEmp);
        ro.setRetData(auctionitemsVoPageInfo);
        ro.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        return ro;
    }
}
