package com.github.youz.server.business.imports.goods;

import com.github.youz.report.enums.BusinessType;
import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.imports.listener.AbstractPartSuccessBusinessListener;
import com.github.youz.server.enums.CustomBusinessType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsImportListener extends AbstractPartSuccessBusinessListener<GoodsImportTemplate> {

    @Override
    protected List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<GoodsImportTemplate> dataList) {
        return null;
    }

    @Override
    protected void invokeImportMethod(List<GoodsImportTemplate> data) {

    }

    @Override
    protected void listenerRowConfig() {
        // 默认表头起始行号为1, 表体起始行号为2. 可根据实际需要调整
        setHeadRowIndex(1);
        setBodyRowIndex(3);
    }

    @Override
    public BusinessType businessType() {
        return CustomBusinessType.GOODS;
    }
}
