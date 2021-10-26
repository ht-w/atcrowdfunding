package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongting
 * @create 2021 10 21 6:06 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailReturnVO {

    // 回报主键信息
    private Integer returnId;

    // 当前栏位需要支持的金额
    private Integer supportMoney;

    // 是否限购，0时无限额，1时有限额
    private Integer signalPurchase;

    // 具体的限额数量
    private Integer purchase;

    // 当前栏位的支持者数量
    private Integer supporterCount;

    // 运费 0时表示包邮
    private Integer freight;

    // 众筹成功多少天后发货
    private Integer returnDate;

    // 回报的详细内容
    private String content;


}