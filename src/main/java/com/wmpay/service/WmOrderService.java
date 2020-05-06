package com.wmpay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.AO.CakeStatisticsAO;
import com.wmpay.bean.AO.DayNumberStatisticsAO;
import com.wmpay.bean.VO.OrderVO;
import com.wmpay.bean.WmAdditionAdmin;
import com.wmpay.bean.WmOrder;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmOrderDAO;
import com.wmpay.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WmOrderService {

    @Autowired
    WmOrderDAO wmOrderDAO;

    @Autowired
    HttpServletRequest request;

    public IPage<OrderVO> getOrderList(PageTools pageTools) {
        Integer adminId = null;
        // 验证订单权限
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE));
        switch (adminType){
            case WM_ADDITION_ADMIN:
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                adminId = admin.getWmAdditionAdminId();
        }
        return wmOrderDAO.selectPageList(new Page<WmOrder>(pageTools.getStart(), pageTools.getLength()), adminId);


    }

    public List<OrderVO> selectOrderByWmPatriarchId(Integer wmPatriarchId) {
        return wmOrderDAO.selectOrderListByPatriarchId(wmPatriarchId);
    }

    public Boolean deleteOrder(Integer wmOrderId) {
        int result = wmOrderDAO.deleteById(wmOrderId);
        return result > 0;
    }

    /**
     * 线状图形统计数据
     * @return
     */
    public List<List<HashMap<String, Object>>> selectDayNumber() {
        Integer adminId = null;
        // 验证订单权限
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE));
        switch (adminType){
            case WM_ADDITION_ADMIN:
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                adminId = admin.getWmAdditionAdminId();
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = DatesUtil.getEndDayOfLastMonth();
        calendar.setTime(date);
        List<List<HashMap<String, Object>>> dayNumberStatistics = wmOrderDAO.
                getDayNumberStatistics(simpleDateFormat.format(date), calendar.getActualMaximum(Calendar.DAY_OF_MONTH), adminId);
        return dayNumberStatistics;
    }

    /**
     * 饼状图形统计数据
     * @param daysType
     * @return
     */
    public CakeStatisticsAO selectCake(Integer daysType) {
        Integer adminId = null;
        // 验证订单权限
        AdminTypeEnum adminType = ((AdminTypeEnum)request.getSession().getAttribute(AdminCommon.USER_TYPE));
        switch (adminType){
            case WM_ADDITION_ADMIN:
                WmAdditionAdmin admin =  ((WmAdditionAdmin)request.getSession().getAttribute(AdminCommon.USER_SESSION));
                adminId = admin.getWmAdditionAdminId();
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        switch (daysType){
            case 1: // 查询今日
                dateStr = simpleDateFormat.format(new Date());
                break;
            case 2: // 查询7天以内的数据
                Date beginDayOfWeek = DatesUtil.getBeginDayOfWeek();
                dateStr = simpleDateFormat.format(beginDayOfWeek);
                break;
            case 3: // 查询30天以内的数据
                Date beginDayOfMonth = DatesUtil.getBeginDayOfMonth();
                dateStr = simpleDateFormat.format(beginDayOfMonth);
                break;
            case 4:
                Date beginDayOfYear = DatesUtil.getBeginDayOfYear();
                dateStr = simpleDateFormat.format(beginDayOfYear);
                break;
        }
        return wmOrderDAO.getStatisticsCake(dateStr, adminId);
    }

    public Integer addOrder(WmOrder wmOrder) {
        int result = wmOrderDAO.insert(wmOrder);
        return result > 0 ? wmOrder.getWmOrderId() : result;
    }

}
