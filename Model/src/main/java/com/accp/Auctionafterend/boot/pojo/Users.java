package com.accp.Auctionafterend.boot.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author accp
 * @since 2023-03-02
 */
@Data
@ApiModel(value = "Users对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableField
    private String usId;

    @ApiModelProperty("账号")
    private String usAccount;

    @ApiModelProperty("密码")
    private String usPwd;

    @ApiModelProperty("身份证")
    private String usStatus;

    @ApiModelProperty("电话")
    private String usPhone;

    @ApiModelProperty("住址")
    private String usAdress;

    @ApiModelProperty("邮政编码")
    private String usPostalCode;

    @ApiModelProperty("余额")
    private Float usPrice;

    @ApiModelProperty("最后登入时间")
    private LocalDateTime usLastLoginTime;

    @ApiModelProperty("头像")
    private String usHeadSculpture;
}
