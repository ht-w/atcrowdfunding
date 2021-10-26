package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 21 1:57 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalTypeVO {
    private Integer id;
    private String name;
    private String remark;
    private List<PortalProjectVO> portalProjectVOList;
}