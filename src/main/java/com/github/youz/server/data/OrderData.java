package com.github.youz.server.data;

import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.business.export.order.OrderRespDTO;

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
}
