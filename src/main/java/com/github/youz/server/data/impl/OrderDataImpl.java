package com.github.youz.server.data.impl;

import com.github.youz.report.imports.bo.ImportInvokeResult;
import com.github.youz.report.util.DateUtil;
import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.business.imports.order.OrderImportTemplate;
import com.github.youz.server.dto.OrderReqDTO;
import com.github.youz.server.dto.OrderRespDTO;
import com.github.youz.server.data.OrderData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class OrderDataImpl implements OrderData {

    @Override
    public PageVO<OrderRespDTO> orderPageInfo(String queryParam, int pageNum, int pageSize) {
        OrderReqDTO reqDTO = new OrderReqDTO();
        reqDTO.setPageNum(pageNum);
        reqDTO.setPageSize(pageSize);

        // feign
        // @FeignClient(value = "order-server", url = "http://order-server:8080")
        // public interface OrderServer {}
        // PageVO<OrderRespDTO> pageInfo = orderServer.orderPageInfo(queryParam, pageNum, pageSize);

        // http
        // String response = HttpUtil.post("order-server", JsonUtil.toJson(reqDTO));
        // PageVO<OrderRespDTO> pageInfo = JsonUtil.toObject(response, PageVO.class);

        // local
        return mockOrderData(reqDTO);
    }

    @Override
    public List<ImportInvokeResult.DataStatus> invokeCheckMethod(List<OrderImportTemplate> dataList) {
        // 调用业务校验方法

        // 如果网络异常，则返回网络错误
        return ImportInvokeResult.networkError(dataList);
    }

    @Override
    public void invokeImportMethod(List<OrderImportTemplate> dataList) {
        // 导入数据. 建议采用MQ异步处理, 由业务保证最终一致性
    }

    /**
     * 模拟订单数据
     *
     * @param reqDTO 订单请求数据对象
     * @return 模拟的订单分页数据对象
     */
    private PageVO<OrderRespDTO> mockOrderData(OrderReqDTO reqDTO) {
        // 模拟数据
        int start = (reqDTO.getPageNum() - 1) * reqDTO.getPageSize();
        int end = start + reqDTO.getPageSize();
        String[] statusName = {"待付款", "付款中", "已完成", "待评价", "已取消"};
        PageVO<OrderRespDTO> pageInfo = new PageVO<>();
        List<OrderRespDTO> list = IntStream.range(start, end)
                .mapToObj(i -> new OrderRespDTO()
                        .setOrderNo(UUID.randomUUID().toString())
                        .setAmount((long) i)
                        .setStatusName(statusName[i % 5])
                        .setOrderTime(DateUtil.now())
                ).collect(Collectors.toList());
        pageInfo.setList(list);
        pageInfo.setTotal(300L);
        return pageInfo;
    }
}
