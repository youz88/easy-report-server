package com.github.youz.server.enums;

import com.github.youz.report.enums.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义业务类型
 */
@Getter
@AllArgsConstructor
public enum CustomBusinessType implements BusinessType {

    ORDER(1, "订单"),

    GOODS(2, "商品"),

    ;

    /**
     * 业务编码
     */
    private final int code;

    /**
     * 描述
     */
    private final String message;
}
