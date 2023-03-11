package com.accp.Auctionafterend.boot.service;

import com.accp.Auctionafterend.boot.pojo.Bidrecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
public interface IBidrecordService extends IService<Bidrecord> {

    /**
     * 添加竞拍对象
     * @param bidrecord
     * @return
     */
    void saveBidrecord(Bidrecord bidrecord);
}
