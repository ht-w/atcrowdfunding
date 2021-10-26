package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 19 12:06 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginVo implements Serializable {

    private Integer id;

    private String username;

    private String email;
}
