package com.xuecheng.common.enums.order;

import com.xuecheng.common.enums.StatusAnnotation;
import com.xuecheng.common.enums.StatusEnum;

/**
 * <p>
 *      订单交易类型状态
 * </p>
 *
 * @Description:
 * @Author: Flippy
 * @Date: 2019/9/25 15:55
 * @since: 2019/9/25 15:55
 */
@StatusAnnotation(name = "订单交易类型状态",code="600")
public enum OrderDealStatusEnum implements StatusEnum {

    ORDER_DEAL_INIT_STATUS("600001","初始态"),
    ORDER_DEAL_PAID_STATUS("600002","已支付"),
    ORDER_DEAL_CANCEL_STATUS("600003","已取消"),
    ORDER_DEAL_CLOSED_STATUS("600004","已关闭"),
    ORDER_DEAL_REFUNDED_STATUS("600005","已退款"),
    ;

    String code;
    String desc;

    OrderDealStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
