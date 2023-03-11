package com.accp.Auctionafterend.boot.service.Impl;

import com.accp.Auctionafterend.boot.mapper.AuctionitemsMapper;
import com.accp.Auctionafterend.boot.pojo.Auctionitems;
import com.accp.Auctionafterend.boot.pojo.vo.AuctionitemsVo;
import com.accp.Auctionafterend.boot.service.IAuctionitemsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@Service
public class AuctionitemsServiceImpl extends ServiceImpl<AuctionitemsMapper, Auctionitems> implements IAuctionitemsService {

    @Autowired
    private AuctionitemsMapper auctionitemsMapper;

    @Override
    public List<Auctionitems> selectByAiId(String aiId) {
        return auctionitemsMapper.selectByAiId(aiId);
    }

    @Override
    public Page<Auctionitems> AuctionitemsPage(Page<Auctionitems> page, QueryWrapper<Auctionitems> queryWrapper) {
        return auctionitemsMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<AuctionitemsVo> selectByIdAuctionitemsVo(String date, String aiId, String isEmp) {
        return auctionitemsMapper.selectByIdAuctionitemsVo(date,aiId,isEmp);
    }

    @Override
    public PageInfo<AuctionitemsVo> selectByIdAuctionitemsVoPage(String date,String aiId, String isEmp, Integer PageNum) {
        PageHelper.startPage(PageNum,1);
        List<AuctionitemsVo> auctionitemsVos = auctionitemsMapper.selectByIdAuctionitemsVo(date, aiId, isEmp);
        PageInfo<AuctionitemsVo> info=new PageInfo<>(auctionitemsVos,2);
        return info;
    }

    @Override
    @Transactional
    public void deleteByAiId(String aiId) {
        auctionitemsMapper.deleteByAiId(aiId);
    }

    @Override
    @Transactional
    public void updateAiId(Auctionitems auctionitems) {
        auctionitemsMapper.updateAiId(auctionitems);
    }
}
