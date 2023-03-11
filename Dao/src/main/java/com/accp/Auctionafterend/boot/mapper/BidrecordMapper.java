package com.accp.Auctionafterend.boot.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.accp.Auctionafterend.boot.pojo.Bidrecord;
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
public interface BidrecordMapper extends BaseMapper<Bidrecord> {

    /**
     * 按物品Id查询该物品出价记录信息；分步查询（二）
     * @param brAiId
     * @return
     */
    List<Bidrecord> selectByBrAiIdOrderByBrPartakeDate(@Param("brAiId") String brAiId);

    Integer selectByIdAndMax(@Param("id") String aiId);
}
