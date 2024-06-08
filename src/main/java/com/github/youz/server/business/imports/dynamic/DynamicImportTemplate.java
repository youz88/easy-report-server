package com.github.youz.server.business.imports.dynamic;

import com.github.youz.report.annotation.ImportCell;
import com.github.youz.report.converter.imports.ImportBigDecimalConverter;
import com.github.youz.report.imports.bo.BasicImportTemplate;
import com.github.youz.report.imports.bo.ImportDynamicColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DynamicImportTemplate extends BasicImportTemplate {

    @ImportCell(index = 0, value = "售卖时间")
    private String saleTime;

    @ImportCell(index = 1, dynamicColumn = true)
    private ImportDynamicColumn spuName;

    @ImportCell(index = 2, value = {"规格", "名称"})
    private String orderTime;

    @ImportCell(index = 3, value = {"规格", "售卖数量"})
    private String statusName;

    @ImportCell(index = 4, value = "支付类型", dynamicColumn = true, converter = ImportBigDecimalConverter.class)
    private List<ImportDynamicColumn<BigDecimal>> payList;

}


