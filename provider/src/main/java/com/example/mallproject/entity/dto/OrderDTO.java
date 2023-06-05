package com.example.mallproject.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String spuName;

    private String address;

    private LocalDateTime createTime;

    private Integer amount;

    private Integer finished;

    private Long uuid;
}
