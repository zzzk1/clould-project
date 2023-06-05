package com.example.mallproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-19
 */
@Data
@Accessors(chain = true)
@TableName("pms_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    private Long userId;

    private Long spuId;

    private String address;

    private LocalDateTime createTime;

    private Integer amount;

    private Integer finished;

    private Long uuid;
}
