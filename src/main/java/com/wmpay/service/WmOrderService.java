package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.VO.OrderVO;
import com.wmpay.bean.WmOrder;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmOrderService {

    @Autowired
    WmOrderDAO wmOrderDAO;

    public IPage<OrderVO> getOrderList(PageTools pageTools) {
        return wmOrderDAO.selectPageList(new Page<WmOrder>(pageTools.getStart(), pageTools.getLength()));
    }

    public Boolean deleteOrder(Integer wmOrderId) {
        int result = wmOrderDAO.deleteById(wmOrderId);
        return result > 0;
    }
}
