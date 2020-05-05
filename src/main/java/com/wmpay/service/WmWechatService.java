package com.wmpay.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.*;
import com.wmpay.dao.*;
import com.wmpay.template.ResponseEnum;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WmWechatService {

    @Autowired
    private WmWechatDAO wmWechatDAO;

    @Autowired
    private WmGradeDAO wmGradeDAO;

    @Autowired
    private WmSquadDAO wmSquadDAO;

    @Autowired
    private WmWechatPayConfigDAO wmWechatPayConfigDAO;

    @Autowired
    private WmAdditionAdminDAO wmAdditionAdminDAO;

    @Autowired
    private WmConfigDAO wmConfigDAO;

    @Autowired
    private WmGradeSquadDAO wmGradeSquadDAO;

    @Autowired
    private WmThirdPayConfigDAO wmThirdPayConfigDAO;

    public WmWechat selectByWechatId(Integer wmWechatId){
        return wmWechatDAO.selectById(wmWechatId);
    }

    public WmWechat selectByOpenId(String openId){
        return wmWechatDAO.selectOne(new QueryWrapper<WmWechat>().eq("openid", openId) );
    }

    public Integer addWmWechat(WmWechat wmWechat) {
        int result = wmWechatDAO.insert(wmWechat);
        return result > 0 ? wmWechat.getWmWechatId() : result;
    }

    /**
     * 查询微信官方配置
     * @param wmWechatPayId
     * @return
     */
    private HashMap<String, Object> selectWeChatPayMapConfigById(Integer wmWechatPayId) {
        HashMap<String,Object> resultMap = new HashMap<>();
        WmWechatPayConfig wmConfigDAO = wmWechatPayConfigDAO.selectById(wmWechatPayId);
        resultMap.put("appId", wmConfigDAO.getAppId());
        resultMap.put("appKey", wmConfigDAO.getAppKey());
        resultMap.put("mchId", wmConfigDAO.getMchId());
        resultMap.put("mchKey", wmConfigDAO.getMchKey());
        resultMap.put("defaultPay", "1");
        return resultMap;
    }

    /**
     * 查询第三方官方配置
     * @param wmThirdPayId
     * @return
     */
    private HashMap<String, Object> selectThirdPayMapConfigById(Integer wmThirdPayId) {
        HashMap<String,Object> resultMap = new HashMap<>();
        WmThirdPayConfig thirdPayConfig =  wmThirdPayConfigDAO.selectById(wmThirdPayId);
        resultMap.put("appId", thirdPayConfig.getAppId() );
        resultMap.put("appKey", thirdPayConfig.getAppKey() );
        resultMap.put("baseUri", thirdPayConfig.getApiBaseUri() );
        resultMap.put("deviceId", thirdPayConfig.getDeviceId() );
        resultMap.put("defaultPay","2");
        return resultMap;
    }

    private HashMap<String,Object> selectSystemPayMapConfig() {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("appId", wmConfigDAO.selectValue("system_wechat_app_id"));
        resultMap.put("appKey", wmConfigDAO.selectValue("system_wechat_app_id"));
        resultMap.put("mchId", wmConfigDAO.selectValue("system_wechat_mch_id"));
        resultMap.put("mchKey", wmConfigDAO.selectValue("system_wechat_mch_key"));
        resultMap.put("defaultPay","0");
        resultMap.put("payLevel","0");
        return resultMap;
    }


    /**
     * 查询当前支付的用户所属ID
     * @return
     */
    public ResponseBean getPayAdminUserId(Integer payUserId,String payType){
        HashMap<String,Object> resultMap = new HashMap<>();
        Integer userId = null;
        String type = "3"; // 默认从第班级查询
        if ("demo".equals(payType)){
            resultMap = selectSystemPayMapConfig();
            return AppResponse.success(resultMap);
        }
        // 查询班级所属年级
        WmAdditionAdmin squadAdminUser = wmAdditionAdminDAO.selectOne(new QueryWrapper<WmAdditionAdmin>()
                .eq("wm_addition_admin_id", payUserId).eq("type", type));
        if (squadAdminUser.getIsDefaultPay() != null){
            switch (squadAdminUser.getIsDefaultPay() ){
                case "0": // 采用默认上级配置
                    WmGradeSquad WmGradeSquad = wmGradeSquadDAO.selectOne(new QueryWrapper<WmGradeSquad>().eq("wm_squad_id", payUserId) );
                    WmAdditionAdmin gradeAdminUser = wmAdditionAdminDAO.selectOne(new QueryWrapper<WmAdditionAdmin>()
                            .eq("wm_addition_admin_id", WmGradeSquad.getWmGradeId() ).eq("type", "2") );
                    if (gradeAdminUser.getIsDefaultPay() != null ){
                        switch (gradeAdminUser.getIsDefaultPay() ){
                            case "0": // 采用默认上级配置
                                // 查询年级所属学校
                                WmGrade wmGrade = wmGradeDAO.selectById(gradeAdminUser.getUserId() );
                                WmAdditionAdmin schoolAdminUser = wmAdditionAdminDAO.selectOne(new QueryWrapper<WmAdditionAdmin>()
                                        .eq("wm_addition_admin_id", wmGrade.getWmSchoolId() ).eq("type", "1") );
                                if (schoolAdminUser.getIsDefaultPay() != null){
                                    switch (schoolAdminUser.getIsDefaultPay()){
                                        case "0": // 如果学校走上级配置  那么默认走官方配置
                                            String systemPayType = wmConfigDAO.selectValue("system_pay_type");
                                            switch (systemPayType){
                                                case "WECHAT":
                                                    resultMap = selectSystemPayMapConfig();
                                                    break;
                                                case "ALIPAY":
                                                    break;
                                                default:
                                                    return AppResponse.error(ResponseEnum.ERROR.status,"错误的系统支付类型");
                                            }
                                            break;
                                        case "1":
                                            resultMap = selectWeChatPayMapConfigById(schoolAdminUser.getWmWechatPayConfigId());
                                            break;
                                        case "2":
                                            resultMap = selectThirdPayMapConfigById(schoolAdminUser.getWmThirdPayConfigId());
                                            break;
                                        default:
                                            return AppResponse.error(ResponseEnum.ERROR.status,"错误的学校默认支付类型");
                                    }
                                    resultMap.put("payLevel", 1);
                                    return AppResponse.success(resultMap);
                                }
                                break;
                            case "1":
                                resultMap = selectWeChatPayMapConfigById(gradeAdminUser.getWmWechatPayConfigId());
                                break;
                            case "2":
                                resultMap = selectThirdPayMapConfigById(gradeAdminUser.getWmThirdPayConfigId());
                                break;
                            default:
                                return AppResponse.error(ResponseEnum.ERROR.status,"错误的年级默认支付类型");
                        }
                        resultMap.put("payLevel",2);
                        return AppResponse.success(resultMap);
                    }
                    break;
                case "1": // 采用微信配置
                    resultMap = selectWeChatPayMapConfigById(squadAdminUser.getWmWechatPayConfigId());
                    break;
                case "2": // 采用支付宝配置
                    resultMap = selectThirdPayMapConfigById(squadAdminUser.getWmThirdPayConfigId());
                    break;
                default:
                    return AppResponse.error(ResponseEnum.ERROR.status,"错误的班级默认支付类型");
            }
            return AppResponse.success(resultMap);
        }
        resultMap = selectSystemPayMapConfig();
        return AppResponse.success(resultMap);
    }
}
