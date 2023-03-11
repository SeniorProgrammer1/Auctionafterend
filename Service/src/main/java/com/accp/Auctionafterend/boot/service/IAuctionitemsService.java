package com.accp.Auctionafterend.boot.service;

import com.accp.Auctionafterend.boot.pojo.Auctionitems;
import com.accp.Auctionafterend.boot.pojo.vo.AuctionitemsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
public interface IAuctionitemsService extends IService<Auctionitems> {

    /**
     * 按物品Id查询物品详细信息；分步查询（一）
     * @param aiId
     * @return
     */
    List<Auctionitems> selectByAiId(@Param("aiId") String aiId);

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<Auctionitems> AuctionitemsPage(Page<Auctionitems> page, QueryWrapper<Auctionitems> queryWrapper);

    /**
     * 查询开始拍卖的物品及结束的物品
     * @param date
     * @param aiId
     * @param isEmp
     * @return
     */
    List<AuctionitemsVo> selectByIdAuctionitemsVo(@Param("date") String date, @Param("id") String aiId, @Param("isEmp") String isEmp);

    PageInfo<AuctionitemsVo> selectByIdAuctionitemsVoPage(String date,String aiId,String isEmp,Integer PageNum);

    /**
     * 按物品Id删除为竞拍的物品
     * @param aiId
     */
    void deleteByAiId(@Param("aiId") String aiId);

    /**
     * 根据物品ID修改物品信息
     * @param auctionitems
     */
    void updateAiId(Auctionitems auctionitems);
}
