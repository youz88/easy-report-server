package com.github.youz.server.business.order;

import com.github.youz.report.data.MockRemoteData;
import com.github.youz.report.enums.BusinessType;
import com.github.youz.report.export.bo.AbstractExportModel;
import com.github.youz.report.export.bo.ExportContext;
import com.github.youz.report.export.bo.ExportData;
import com.github.youz.report.export.bo.ExportHead;
import com.github.youz.report.export.handler.AbstractDataAssemblyExportHandler;
import com.github.youz.report.web.dto.order.OrderRespDTO;
import com.github.youz.report.web.vo.PageVO;
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

    private final MockRemoteData mockRemoteData;

    @Override
    protected long queryTotal(String queryParam) {
        return 205L;
    }

    @Override
    public BusinessType businessType() {
        return BusinessType.ORDER;
    }

    @Override
    protected ExportHead handleHead(ExportContext context) {
        // 组装表头
        AbstractExportModel abstractExport = OrderBO.assemblyHead(context);

        // 返回表头对象
        return ExportHead.assemblyData(abstractExport, context);
    }

    @Override
    protected ExportData handleData(ExportContext context) {
        // 查询订单列表
        PageVO<OrderRespDTO> pageDTO = mockRemoteData.orderPageInfo(context.getQueryParam(), context.getPageNum(), context.getPageSize());
        if (Objects.isNull(pageDTO) || CollectionUtils.isEmpty(pageDTO.getList())) {
            return null;
        }

        // 查询依赖数据, 如订单只有用户ID, 需要展示用户名称则可以在此处查询用户服务

        // 组装表体
        AbstractExportModel abstractExport = OrderBO.assemblyData(pageDTO.getList(), context);

        // 返回表体对象
        return ExportData.assemblyData(abstractExport);
    }
}
