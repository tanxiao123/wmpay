package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weimai.tools.Wm;
import com.weimai.tools.encrypt.Des;
import com.wmpay.bean.VO.PayConfigVO;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmThirdPayConfig;
import com.wmpay.bean.WmWechatPayConfig;
import com.wmpay.dao.WmAdditionAdminDAO;
import com.wmpay.dao.WmThirdPayConfigDAO;
import com.wmpay.dao.WmWechatPayConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmPayConfigurationService {

    @Autowired
    private WmThirdPayConfigDAO wmThirdPayConfigDAO;

    @Autowired
    private WmWechatPayConfigDAO wmWeChatPayConfigDAO;

    @Autowired
    private WmAdditionAdminDAO wmAdditionAdminDAO;


    /**
     * 查询默认的支付配置  当前表结构为一对多 为防止多需求
     * @param wmKeyId
     * @return
     */
    public PayConfigVO getPayConfig(Integer wmKeyId) {
        PayConfigVO vo = new PayConfigVO();
        WmAdditionAdmin wmAdditionAdmin = wmAdditionAdminDAO.selectById(wmKeyId);
        if (wmAdditionAdmin != null){
            WmThirdPayConfig thirdPayResult = wmThirdPayConfigDAO.selectById(wmAdditionAdmin.getWmThirdPayConfigId() );
            WmWechatPayConfig wmWeChatPayResult = wmWeChatPayConfigDAO.selectById(wmAdditionAdmin.getWmWechatPayConfigId() );
            vo.setWmThirdPayConfig(thirdPayResult);
            vo.setWeChatPayConfig(wmWeChatPayResult);
            vo.setDefaultPay(wmAdditionAdmin.getIsDefaultPay() );
        }
        return vo;
    }

    public String getDeviceId() {
        boolean is = true; // 循环条件
        int i = 0; // 循环标识
        String deviceId = "";
        while (is){
            if (i > 10) break;
            deviceId = Wm.getRandom(6);
            WmThirdPayConfig result = wmThirdPayConfigDAO.selectOne( new QueryWrapper<WmThirdPayConfig>().eq("device_id",deviceId) );
            if (result == null ){
                is = false;
                break;
            }
            i++;
        }
        return !is ? deviceId : "";
    }

    /**
     * 修改第三方配置
     * @param payConfiguration
     * @return
     */
    public Boolean editThirdPayConfig(WmThirdPayConfig payConfiguration) {
        int result = wmThirdPayConfigDAO.updateById(payConfiguration);
        return result > 0;
    }

    /**
     * 修改微信官方配置
     * @param payconfig
     * @return
     */
    public Boolean editWechatPayConfig(WmWechatPayConfig payconfig) {
        int result = wmWeChatPayConfigDAO.updateById(payconfig);
        return result > 0;
    }


}
