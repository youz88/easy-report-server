package com.github.youz.server.dto;

import lombok.Data;

/**
 * 订单请求DTO
 */
@Data
public class OrderReqDTO {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
