package com.github.youz.server.business.goods;

import lombok.Data;

/**
 * 商品销售请求DTO
 */
@Data
public class GoodsReqDTO {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;
}
