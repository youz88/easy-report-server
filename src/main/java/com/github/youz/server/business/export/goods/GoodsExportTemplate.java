package com.github.youz.server.business.export.goods;

import com.alibaba.excel.annotation.ExcelProperty;
import com.github.youz.report.annotation.DateTimeFormat;
import com.github.youz.report.constant.ReportConst;
import com.github.youz.report.export.bo.BasicExportTemplate;
import com.github.youz.report.export.bo.DynamicColumn;
import com.github.youz.report.export.bo.ExportContext;
import com.github.youz.report.util.JsonUtil;
import com.github.youz.server.dto.GoodsRespDTO;
import com.github.youz.server.dto.PayTypeRespDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 商品导出对象
 */
@Data
@Accessors(chain = true)
public class GoodsExportTemplate {

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("售卖时间")
    private Integer saleTime;

    @ExcelProperty("商品名称")
    private String spuName;

    @ExcelProperty({"规格", "名称"})
    private String skuName;

    @ExcelProperty({"规格", "售卖数量"})
    private Integer saleNum;

    /**
     * 支付信息
     */
    @ExcelProperty
    private List<DynamicColumn> payList;

    /**
     * 构造导出导出表头
     *
     * @param context     导出任务上下文
     * @param payTypeList 支付类型列表
     * @return 表头
     */
    public static BasicExportTemplate assemblyHead(ExportContext context, List<PayTypeRespDTO> payTypeList) {
        List<DynamicColumn> payList = payTypeList.stream()
                .map(payType -> DynamicColumn.buildHead(payType.getPayTypeId(),
                        "支付方式", payType.getPayTypeName()))
                .collect(Collectors.toList());
        GoodsExportTemplate goodsExportTemplate = new GoodsExportTemplate()
                .setPayList(payList);
        return BasicExportTemplate.assemblyDynamicHead(goodsExportTemplate, context);
    }

    /**
     * 构造导出导出对象
     *
     * @param sourceList 导出原始数据
     * @param context    导出任务上下文
     * @return 导出对象
     */
    public static BasicExportTemplate assemblyData(List<GoodsRespDTO> sourceList, ExportContext context) {
        // 获取动态模版对象
        GoodsExportTemplate dynamicTemplate = (GoodsExportTemplate) context.getDynamicTemplate();

        // 1. 初始化导出模版对象
        List<GoodsExportTemplate> dataList = new ArrayList<>();

        // 2. 格式化导出模板
        for (GoodsRespDTO source : sourceList) {
            GoodsExportTemplate exportTemplate = JsonUtil.convert(source, GoodsExportTemplate.class);

            // 设置动态列标题数据
            List<DynamicColumn> payTypeList = dynamicTemplate.getPayList().stream()
                    .map(payType -> {
                        DynamicColumn dynamicColumn = JsonUtil.convert(payType, DynamicColumn.class);

                        // 根据唯一表示判断是否匹配支付类型
                        if (source.getPayType().equals(dynamicColumn.getUniqueKey())) {
                            dynamicColumn.setData(source.getPayAmount());
                        } else {
                            dynamicColumn.setData(ReportConst.MINUS_SYMBOL);
                        }
                        return dynamicColumn;
                    }).collect(Collectors.toList());
            exportTemplate.setPayList(payTypeList);

            dataList.add(exportTemplate);
        }

        // 3. 返回导出业务对象, 过滤导出字段
        return BasicExportTemplate.assemblyBody(dataList, context);
    }

}
