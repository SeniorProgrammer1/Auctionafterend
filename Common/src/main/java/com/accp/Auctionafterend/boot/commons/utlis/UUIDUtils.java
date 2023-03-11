package com.accp.Auctionafterend.boot.commons.utlis;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 返回uuid的值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
