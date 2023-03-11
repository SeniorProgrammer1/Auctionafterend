package com.accp.Auctionafterend.boot.service.Impl;

import com.accp.Auctionafterend.boot.mapper.BidrecordMapper;
import com.accp.Auctionafterend.boot.pojo.Bidrecord;
import com.accp.Auctionafterend.boot.service.IBidrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@Service
public class BidrecordServiceImpl extends ServiceImpl<BidrecordMapper, Bidrecord> implements IBidrecordService {

    @Autowired
    private BidrecordMapper bidrecordMapper;

    @Override
    @Transactional
    public void saveBidrecord(Bidrecord bidrecord) {
        bidrecordMapper.insert(bidrecord);
    }
}
