package com.github.youz.server.business.imports.goods;

import com.github.youz.report.imports.bo.ImportContext;
import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.imports.listener.AbstractPartSuccessBusinessListener;

import java.util.List;

public class GoodsImportListener extends AbstractPartSuccessBusinessListener<GoodsImportTemplate> {

    public GoodsImportListener(Class<GoodsImportTemplate> clazz, ImportContext importContext) {
        super(clazz, importContext);
    }

    @Override
    protected List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<GoodsImportTemplate> dataList) {
        return ImportInvokeResult.networkError(dataList);
    }

    @Override
    protected void invokeImportMethod(List<GoodsImportTemplate> data) {

    }

    @Override
    protected void listenerRowConfig() {
        setHeadRowIndex(1);
        setBodyRowIndex(3);
    }
}
