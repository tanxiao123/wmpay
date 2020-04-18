package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmpay.bean.WmOrder;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmOrderService {

    @Autowired
    WmOrderDAO wmOrderDAO;

    public IPage<WmOrder> getOrderList(PageTools pageTools) {

        return null;
    }

    public Boolean deleteOrder(Integer wmOrderId) {
        int result = wmOrderDAO.deleteById(wmOrderId);
        return result > 0;
    }
}
