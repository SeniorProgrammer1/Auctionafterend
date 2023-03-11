package com.accp.Auctionafterend.boot.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@ApiModel(value = "Bidrecord对象", description = "")
@Data
@ToString
public class Bidrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键Id")
    @TableField
    private String brId;

    @ApiModelProperty("物品Id")
    private String brAiId;

    @ApiModelProperty("竞拍人")
    private String brBidder;

    @ApiModelProperty("参与时间")
    private String brPartakeDate;

    @ApiModelProperty("竞拍价格")
    private Float brPrice;
}
