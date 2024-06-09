package com.github.youz.server.business.imports.order;

import com.github.youz.report.annotation.ImportCell;
import com.github.youz.report.imports.bo.BasicImportTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单导入模板(classpath: test/order.xlsx)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderImportTemplate extends BasicImportTemplate {

    @ImportCell(index = 0, value = "序号")
    private String serialNumber;

    @ImportCell(index = 1, value = "订单号")
    private String orderNo;

    @ImportCell(index = 2, value = "下单时间")
    private String orderTime;

    @ImportCell(index = 3, value = "订单状态")
    private String statusName;

    @ImportCell(index = 4, value = "订单金额")
    private String amount;

}


