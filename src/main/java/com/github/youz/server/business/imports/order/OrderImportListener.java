package com.github.youz.server.business.imports.order;

import com.github.youz.report.imports.bo.ImportContext;
import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.imports.listener.AbstractPartSuccessBusinessListener;
import com.github.youz.report.util.ApplicationContextUtil;
import com.github.youz.server.data.OrderData;

import java.util.List;

public class OrderImportListener extends AbstractPartSuccessBusinessListener<OrderImportTemplate> {

    public OrderImportListener(Class<OrderImportTemplate> clazz, ImportContext importContext) {
        super(clazz, importContext);
    }

    @Override
    protected List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<OrderImportTemplate> dataList) {
        // 是否需要业务校验，如果不需要，直接返回空即可
        return ApplicationContextUtil.getBean(OrderData.class).invokeCheckMethod(dataList);
    }

    @Override
    protected void invokeImportMethod(List<OrderImportTemplate> dataList) {
        ApplicationContextUtil.getBean(OrderData.class).invokeImportMethod(dataList);
    }

}
