package com.wmpay.service;

import com.weimai.tools.Wm;
import com.weimai.tools.encrypt.Des;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmThirdPayConfig;
import com.wmpay.bean.WmWechatPayConfig;
import com.wmpay.dao.WmAdditionAdminDAO;
import com.wmpay.dao.WmThirdPayConfigDAO;
import com.wmpay.dao.WmWechatPayConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WmAdditionAdminService {

    @Autowired
    private WmAdditionAdminDAO wmAdditionAdminDAO;

    @Autowired
    private WmThirdPayConfigDAO thirdPayConfigDAO;

    @Autowired
    private WmWechatPayConfigDAO wechatPayConfigDAO;

    /**
     * 保存新增的代理用户
     * @param wmAdditionAdmin
     * @return
     */
    public Boolean saveAddition(WmAdditionAdmin wmAdditionAdmin) {
        Date date = new Date();
        wmAdditionAdmin.setLogintime(date);
        wmAdditionAdmin.setCreatedTime(date);
        wmAdditionAdmin.setUpdatedTime(date);
        wmAdditionAdmin.setSalt(Wm.getRandom(8));
        wmAdditionAdmin.setStatus("1");
        int result = wmAdditionAdminDAO.insert(wmAdditionAdmin);
        if  (result > 0){
            wmAdditionAdmin.setPassword(Des.encode(wmAdditionAdmin.getSalt(),wmAdditionAdmin.getPassword() ) );
            int upAdmin = wmAdditionAdminDAO.updateById(wmAdditionAdmin);
            if (upAdmin > 0){
                // 新增支付配置
                WmThirdPayConfig wmPayConfiguration = new WmThirdPayConfig();
                int thirdPayResult = thirdPayConfigDAO.insert(wmPayConfiguration);

                WmWechatPayConfig wechatPayConfig = new WmWechatPayConfig();
                wechatPayConfig.setPayType("JSAPI");
                int wePayResult  = wechatPayConfigDAO.insert(wechatPayConfig);

                return thirdPayResult > 0 && wePayResult > 0;
            }
        }
        return false;
    }


    public Boolean updateAdditionById(WmAdditionAdmin wmAdditionAdmin) {
        int result = wmAdditionAdminDAO.updateById(wmAdditionAdmin);
        return result > 0;
    }

}
