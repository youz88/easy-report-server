package com.github.youz.server.business.goods;

import com.github.youz.report.constant.ReportConst;
import com.github.youz.report.enums.BusinessType;
import com.github.youz.report.export.bo.BasicExportTemplate;
import com.github.youz.report.export.bo.ExportContext;
import com.github.youz.report.export.bo.ExportData;
import com.github.youz.report.export.bo.ExportHead;
import com.github.youz.report.export.handler.AbstractDataAssemblyExportHandler;
import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.data.GoodsData;
import com.github.youz.server.enums.CustomBusinessType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 商品导出
 */
@Component
@RequiredArgsConstructor
public class GoodsHandler extends AbstractDataAssemblyExportHandler {

    private final GoodsData goodsData;

    @Override
    protected long queryTotal(String queryParam) {
        PageVO<GoodsRespDTO> pageVO = goodsData.goodsPageInfo(queryParam, ReportConst.ONE, ReportConst.ONE);
        return pageVO.getTotal();
    }

    @Override
    protected ExportHead handleHead(ExportContext context) {
        // 查询支付类型, 组装动态表头
        List<PayTypeRespDTO> payTypeList = goodsData.payTypeList(context.getQueryParam());

        // 组装表头
        BasicExportTemplate exportTemplate = GoodsTemplate.assemblyHead(context, payTypeList);

        // 返回表头对象
        return ExportHead.assemblyData(exportTemplate, context);
    }

    @Override
    protected ExportData handleBody(ExportContext context) {
        // 查询商品列表
        PageVO<GoodsRespDTO> pageDTO = goodsData.goodsPageInfo(context.getQueryParam(), context.getPageNum(), context.getPageSize());
        if (Objects.isNull(pageDTO) || CollectionUtils.isEmpty(pageDTO.getList())) {
            return null;
        }

        // 组装表体
        BasicExportTemplate exportTemplate = GoodsTemplate.assemblyData(pageDTO.getList(), context);

        // 返回表体对象
        return ExportData.assemblyData(exportTemplate);
    }

    @Override
    public BusinessType businessType() {
        return CustomBusinessType.GOODS;
    }
}
