package com.wmpay.bean.AO;

public class CakeStatisticsAO {
    // 总下单量
    private Integer countOrder;
    // 成交量
    private Integer successOrder;
    // 未成交量
    private Integer failOrder;
    // 总成交金额
    private Integer sumSuccessTotalFee;
    // 未成交金额
    private Integer sumFailTotalFee;

    public Integer getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(Integer countOrder) {
        this.countOrder = countOrder;
    }

    public Integer getSuccessOrder() {
        return successOrder;
    }

    public void setSuccessOrder(Integer successOrder) {
        this.successOrder = successOrder;
    }

    public Integer getFailOrder() {
        return failOrder;
    }

    public void setFailOrder(Integer failOrder) {
        this.failOrder = failOrder;
    }
}
