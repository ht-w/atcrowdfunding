package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongting
 * @create 2021 10 25 7:03 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO implements Serializable {

    private Integer id;

    private String receiveName;

    private String phoneNum;

    private String address;

    private Integer memberId;

}