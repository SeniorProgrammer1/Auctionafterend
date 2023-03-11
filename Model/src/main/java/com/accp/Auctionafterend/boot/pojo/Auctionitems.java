package com.accp.Auctionafterend.boot.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
@ApiModel(value = "Auctionitems对象", description = "")
@Data
@ToString
public class Auctionitems implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键，物品Id")
    @TableField
    private String aiId;

    @ApiModelProperty("拍卖人Id")
    private String aiUsId;

    @ApiModelProperty("拍卖人名称")
    private String aiUsAccount;

    @ApiModelProperty("物品名称")
    private String aiName;

    @ApiModelProperty("物品描述")
    private String aiDescribe;

    @ApiModelProperty("开始时间")
    private LocalDate aiStartDate;

    @ApiModelProperty("结束时间")
    private LocalDate aiFinishDate;

    @ApiModelProperty("起拍价")
    private Float aiPrice;

    @ApiModelProperty("低价")
    private Float aiLowPrice;

    @ApiModelProperty("物品照片")
    private String aiItemPhoto;

    @ApiModelProperty("出价记录")
    @TableField(exist = false)
    private List<Bidrecord> bidrecord;
}
