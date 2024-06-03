package com.github.youz.server.business.goods;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品销售响应DTO
 */
@Data
@Accessors(chain = true)
public class GoodsRespDTO {

    /**
     * 销售时间
     */
    private Long saleTime;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 规格名称
     */
    private String skuName;

    /**
     * 售卖数量
     */
    private Integer saleNum;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 支付金额
     */
    private Integer payAmount;
}
