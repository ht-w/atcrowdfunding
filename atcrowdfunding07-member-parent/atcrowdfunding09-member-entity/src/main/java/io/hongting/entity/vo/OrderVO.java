package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 26 3:33 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable {

    // 主键
    private Integer id;

    // 订单号
    private String orderNum;

    // 支付宝流水单号
    private String payOrderNum;

    // 订单金额
    private Double orderAmount;

    // 是否开发票
    private Integer invoice;

    // 发票抬头
    private String invoiceTitle;

    // 备注
    private String orderRemark;

    // address的id
    private String addressId;

    // 级联对象orderProjectVO
    private OrderProjectVO orderProjectVO;

}