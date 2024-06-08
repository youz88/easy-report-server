package com.github.youz.server.business.export.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.github.youz.report.annotation.DateTimeFormat;
import com.github.youz.report.annotation.DefaultValueFormat;
import com.github.youz.report.annotation.MoneyFormat;
import com.github.youz.report.export.bo.BasicExportTemplate;
import com.github.youz.report.export.bo.ExportContext;
import com.github.youz.report.util.JsonUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


/**
 * 订单导出对象
 */
@Data
@Accessors(chain = true)
public class OrderExportTemplate {

    @ExcelProperty("序号")
    private String index;

    @ExcelProperty("订单号")
    private String orderNo;

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("下单时间")
    private Integer orderTime;

    @DefaultValueFormat("未知")
    @ExcelProperty("订单状态")
    private String statusName;

    @MoneyFormat("%.2f")
    @ExcelProperty("订单金额")
    private Long amount;

    /**
     * 构造导出导出表头
     *
     * @param context 导出任务上下文
     * @return 表头
     */
    public static BasicExportTemplate assemblyHead(ExportContext context) {
        return BasicExportTemplate.assemblyFixHead(OrderExportTemplate.class, context);
    }

    /**
     * 构造导出导出对象
     *
     * @param sourceList 导出原始数据
     * @param context    导出任务上下文
     * @return 导出对象
     */
    public static BasicExportTemplate assemblyData(List<OrderRespDTO> sourceList, ExportContext context) {
        int rowIndex = context.getRowIndex();

        // 1. 初始化导出模版对象
        List<OrderExportTemplate> dataList = new ArrayList<>();

        // 2. 格式化导出模板
        for (OrderRespDTO source : sourceList) {
            OrderExportTemplate exportTemplate = JsonUtil.convert(source, OrderExportTemplate.class);
            exportTemplate.setIndex(String.valueOf(rowIndex++));
            dataList.add(exportTemplate);
        }

        // 3. 返回导出业务对象, 过滤导出字段
        return BasicExportTemplate.assemblyBody(dataList, context);
    }

}
