package com.accp.Auctionafterend.boot.conditions;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "AuctionConditions条件封装对象", description = "")
public class AuctionConditions {

    private String aiName;

    private String aiDescribe;

    private float aiPrice;

    private String aiStartDate;

    private String aiFinishDate;

    private Integer pageNum;
}
