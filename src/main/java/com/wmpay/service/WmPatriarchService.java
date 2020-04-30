package com.wmpay.service;

import com.wmpay.bean.AO.WmPatriarchAO;
import com.wmpay.bean.WmPatriarch;
import com.wmpay.dao.WmPatriarchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WmPatriarchService {

    @Autowired
    private WmPatriarchDAO wmPatriarchDAO;

    /**
     * 获取家长详细信息接口
     * @param openid
     * @return
     */
    public WmPatriarchAO getWmPatriarchInfoByOpenId(String openid) {
        return wmPatriarchDAO.selectPatriarchInfoByOpenId(openid);
    }
}
