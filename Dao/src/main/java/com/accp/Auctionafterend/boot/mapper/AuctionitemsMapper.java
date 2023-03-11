package com.accp.Auctionafterend.boot.mapper;
import java.util.List;

import com.accp.Auctionafterend.boot.conditions.AuctionConditions;
import com.accp.Auctionafterend.boot.pojo.vo.AuctionitemsVo;
import org.apache.ibatis.annotations.Param;

import com.accp.Auctionafterend.boot.pojo.Auctionitems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@Repository
public interface AuctionitemsMapper extends BaseMapper<Auctionitems> {

    /**
     * 按物品Id查询物品详细信息；分步查询（一）
     * @param aiId
     * @return
     */
    List<Auctionitems> selectByAiId(@Param("aiId") String aiId);

    /**
     * 查询开始拍卖的物品及结束的物品
     * @param date
     * @param aiId
     * @param isEmp
     * @return
     */
    List<AuctionitemsVo> selectByIdAuctionitemsVo(@Param("date") String date,@Param("id") String aiId,@Param("isEmp") String isEmp);

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
