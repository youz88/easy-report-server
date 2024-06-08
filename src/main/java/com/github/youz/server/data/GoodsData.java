package com.github.youz.server.data;

import com.github.youz.report.web.vo.PageVO;
import com.github.youz.server.business.export.goods.GoodsRespDTO;
import com.github.youz.server.business.export.goods.PayTypeRespDTO;

import java.util.List;

public interface GoodsData {

    /**
     * 获取商品分页信息
     *
     * @param queryParam 查询参数
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 商品信息
     */
    PageVO<GoodsRespDTO> goodsPageInfo(String queryParam, int pageNum, int pageSize);

    /**
     * 根据查询参数获取商品详情响应对象
     *
     * @param queryParam 查询参数
     * @return SpuRespDTO 商品详情响应对象
     */
    List<PayTypeRespDTO> payTypeList(String queryParam);
}
