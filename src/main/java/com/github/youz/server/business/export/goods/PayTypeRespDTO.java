package com.github.youz.server.business.export.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付类型响应DTO
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class PayTypeRespDTO {

    /**
     * 支付类型ID
     */
    private Integer payTypeId;

    /**
     * 支付类型名称
     */
    private String payTypeName;

}
