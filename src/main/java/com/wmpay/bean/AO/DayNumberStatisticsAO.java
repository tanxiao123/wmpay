package com.wmpay.bean.AO;

public class DayNumberStatisticsAO {

    // 日期时间
    private String createTime;
    // 当前日期时间所交易数量
    private Integer number;
    // 交易金额
    private Integer totalFee;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }
}
