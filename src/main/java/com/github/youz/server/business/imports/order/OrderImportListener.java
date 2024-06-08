package com.github.youz.server.business.imports.order;

import com.github.youz.report.imports.bo.ImportContext;
import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.imports.listener.AbstractPartSuccessBusinessListener;

import java.util.List;

public class OrderImportListener extends AbstractPartSuccessBusinessListener<OrderImportTemplate> {

    public OrderImportListener(Class<OrderImportTemplate> clazz, ImportContext importContext) {
        super(clazz, importContext);
    }

    @Override
    protected List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<OrderImportTemplate> dataList) {
        return ImportInvokeResult.networkError(dataList);
    }

    @Override
    protected void invokeImportMethod(List<OrderImportTemplate> data) {

    }

}
