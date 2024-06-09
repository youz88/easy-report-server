package com.github.youz.server.business.export.order;

import com.github.youz.report.constant.ReportConst;
import com.github.youz.report.enums.BusinessType;
import com.github.youz.report.export.bo.BasicExportTemplate;
import com.github.youz.report.export.bo.ExportContext;
import com.github.youz.report.export.bo.ExportData;
import com.github.youz.report.export.bo.ExportHead;
import com.github.youz.report.export.handler.AbstractDataAssemblyExportHandler;
import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.data.OrderData;
import com.github.youz.server.dto.OrderRespDTO;
import com.github.youz.server.enums.CustomBusinessType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 订单导出
 */
@Component
@RequiredArgsConstructor
public class OrderExportHandler extends AbstractDataAssemblyExportHandler {

    private final OrderData orderData;

    @Override
    protected long queryTotal(String queryParam) {
        PageVO<OrderRespDTO> pageVO = orderData.orderPageInfo(queryParam, ReportConst.ONE, ReportConst.ONE);
        return pageVO.getTotal();
    }

    @Override
    public BusinessType businessType() {
        return CustomBusinessType.ORDER;
    }

    @Override
    protected ExportHead handleHead(ExportContext context) {
        // 组装表头
        BasicExportTemplate exportTemplate = OrderExportTemplate.assemblyHead(context);

        // 返回表头对象
        return ExportHead.assemblyData(exportTemplate, context);
    }

    @Override
    protected ExportData handleBody(ExportContext context) {
        // 查询订单列表
        PageVO<OrderRespDTO> pageDTO = orderData.orderPageInfo(context.getQueryParam(), context.getPageNum(), context.getPageSize());
        if (Objects.isNull(pageDTO) || CollectionUtils.isEmpty(pageDTO.getList())) {
            return null;
        }

        // 查询依赖数据, 如订单只有用户ID, 需要展示用户名称则可以在此处查询用户服务

        // 组装表体
        BasicExportTemplate exportTemplate = OrderExportTemplate.assemblyData(pageDTO.getList(), context);

        // 返回表体对象
        return ExportData.assemblyData(exportTemplate);
    }
}
