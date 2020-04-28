package com.wmpay.bean.AO;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class PayAO {

    // 订单信息描述
    @NotBlank(message = "订单描述不可为空")
    private String body;

    // 下单金额 (分计算)
    @NotNull(message = "下单金额不可为空")
    private Integer totalFee;

    // 下单家长ID
    @NotNull(message = "下单家长ID不可为空")
    private Integer wmPatriarchId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }


    public Integer getWmPatriarchId() {
        return wmPatriarchId;
    }

    public void setWmPatriarchId(Integer wmPatriarchId) {
        this.wmPatriarchId = wmPatriarchId;
    }
}
