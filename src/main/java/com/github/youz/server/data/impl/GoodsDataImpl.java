package com.github.youz.server.data.impl;

import com.github.youz.report.util.DateUtil;
import com.github.youz.report.util.JsonUtil;
import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.business.export.goods.GoodsReqDTO;
import com.github.youz.server.business.export.goods.GoodsRespDTO;
import com.github.youz.server.business.export.goods.PayTypeRespDTO;
import com.github.youz.server.data.GoodsData;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GoodsDataImpl implements GoodsData {

    @Override
    public PageVO<GoodsRespDTO> goodsPageInfo(String queryParam, int pageNum, int pageSize) {
        GoodsReqDTO reqDTO = JsonUtil.toObject(queryParam, GoodsReqDTO.class);
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
    public List<PayTypeRespDTO> payTypeList(String queryParam) {
        PayTypeRespDTO alipayPay = new PayTypeRespDTO(1, "支付宝");
        PayTypeRespDTO weChatPay = new PayTypeRespDTO(2, "微信");
        PayTypeRespDTO unionPay = new PayTypeRespDTO(3, "银联");
        return Arrays.asList(alipayPay, weChatPay, unionPay);
    }

    /**
     * 模拟订单数据
     *
     * @param reqDTO 订单请求数据对象
     * @return 模拟的订单分页数据对象
     */
    private PageVO<GoodsRespDTO> mockOrderData(GoodsReqDTO reqDTO) {
        // 模拟数据
        int start = (reqDTO.getPageNum() - 1) * reqDTO.getPageSize();
        int end = start + reqDTO.getPageSize();
        String[] spuName = {"洋芋", "土豆", "马铃薯", "Potato"};
        String[] skuName = {"小份", "中份", "大份"};
        Integer[] payType = {1, 2, 3};
        PageVO<GoodsRespDTO> pageInfo = new PageVO<>();
        List<GoodsRespDTO> list = IntStream.range(start, end)
                .mapToObj(i -> new GoodsRespDTO()
                        .setSpuName(spuName[i % 4])
                        .setSaleNum(i)
                        .setSkuName(skuName[i % 3])
                        .setSaleTime(DateUtil.now())
                        .setPayType(payType[i % 3])
                        .setPayAmount(i)
                ).collect(Collectors.toList());
        pageInfo.setList(list);
        pageInfo.setTotal(300L);
        return pageInfo;
    }
}
