package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName bb_order
 */
@TableName(value ="bb_order")
@Data
public class BbOrder implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     *  创建时间
     */
    private LocalDateTime createTime;

    /**
     *  创建人
     */
    private String creatorBy;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 主厨id
     */
    private Integer canteenId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}