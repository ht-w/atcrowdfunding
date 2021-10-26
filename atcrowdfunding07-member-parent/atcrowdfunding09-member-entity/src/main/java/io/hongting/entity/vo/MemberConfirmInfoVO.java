package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 20 3:10 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberConfirmInfoVO implements Serializable {
    //	易付宝账号
    private String paynum;

    //	法人身份证号
    private String cardnum;

}
