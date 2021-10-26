package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 24 2:54 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProjectVO implements Serializable {

    private Integer id;

    private String projectName;

    private String launchName;

    private String returnContent;

    private Integer returnCount;

    private Integer supportPrice;

    private Integer freight;

    private Integer orderId;

    //	是否限制单笔购买数量，0 表示不限购，1 表示限购
    private Integer signalPurchase;

    //	如果单笔限购，那么具体的限购数量
    private Integer purchase;


}