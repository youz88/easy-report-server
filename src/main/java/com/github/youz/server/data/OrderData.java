package com.github.youz.server.data;

import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.business.imports.order.OrderImportTemplate;
import com.github.youz.server.dto.OrderRespDTO;

import java.util.List;

public interface OrderData {

    /**
     * 获取订单分页信息
     *
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 订单分页信息，包括订单列表和分页信息
     */
    PageVO<OrderRespDTO> orderPageInfo(String queryParam, int pageNum, int pageSize);

    /**
     * 调用校验方法
     *
     * @param dataList 订单导入模板列表
     * @return 返回一个ImportInvokeResult.DataStatus类型的列表，包含校验结果
     */
    List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<OrderImportTemplate> dataList);

    /**
     * 调用校验方法
     *
     * @param dataList 订单导入模板列表，用于进行校验
     */
    void invokeImportMethod(List<OrderImportTemplate> dataList);
}
