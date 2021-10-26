package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 20 3:08 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLaunchInfoVO implements Serializable {

    //	简单介绍
    private String descriptionSimple;

    //	详细介绍
    private String descriptionDetail;

    //	联系电话
    private String phoneNum;

    //	客服电话
    private String serviceNum;

}
