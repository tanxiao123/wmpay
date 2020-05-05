package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /**
     * 根据家长名称获取家长信息
     * @param name
     * @return
     */
    public WmPatriarch getWmPatriarchInfoByName(String name) {
        return wmPatriarchDAO.selectOne(new QueryWrapper<WmPatriarch>().eq("name", name));
    }


    /**
     * 添加家长信息
     * @param wmPatriarch
     * @return
     */
    public Integer addWmPatriarch(WmPatriarch wmPatriarch) {
        int result = wmPatriarchDAO.insert(wmPatriarch);
        return result > 0 ? wmPatriarch.getWmPatriarchId() : result;
    }
}
