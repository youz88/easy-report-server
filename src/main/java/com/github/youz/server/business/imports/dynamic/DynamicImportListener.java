package com.github.youz.server.business.imports.dynamic;

import com.github.youz.report.imports.bo.ImportContext;
import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.imports.listener.AbstractPartSuccessBusinessListener;

import java.util.List;

public class DynamicImportListener extends AbstractPartSuccessBusinessListener<DynamicImportTemplate> {

    public DynamicImportListener(Class<DynamicImportTemplate> clazz, ImportContext importContext) {
        super(clazz, importContext);
    }

    @Override
    protected List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<DynamicImportTemplate> dataList) {
        return ImportInvokeResult.networkError(dataList);
    }

    @Override
    protected void invokeImportMethod(List<DynamicImportTemplate> data) {

    }

    @Override
    protected void listenerRowConfig() {
        setHeadRowIndex(1);
        setBodyRowIndex(3);
    }
}
