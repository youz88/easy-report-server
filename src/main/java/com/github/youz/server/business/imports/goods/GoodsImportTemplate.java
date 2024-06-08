package com.github.youz.server.business.imports.goods;

import com.github.youz.report.annotation.ImportCell;
import com.github.youz.report.imports.bo.BasicImportTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsImportTemplate extends BasicImportTemplate {

    @ImportCell(index = 0, value = "售卖时间")
    private String saleTime;

    @ImportCell(index = 1, value = "商品名称")
    private String spuName;

    @ImportCell(index = 2, value = {"规格", "名称"})
    private String orderTime;

    @ImportCell(index = 3, value = {"规格", "售卖数量"})
    private String statusName;

    @ImportCell(index = 4, value = {"支付方式", "支付宝"})
    private String alipayPay;

    @ImportCell(index = 5, value = {"支付方式", "微信"})
    private String weChatPay;

    @ImportCell(index = 6, value = {"支付方式", "银联"})
    private String unionPay;

}


