package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongting
 * @create 2021 10 18 3:31 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO {
    private String loginAcct;

    private String userPswd;

    private String userName;

    private String email;

    private String phoneNum;

    private String code;

}
