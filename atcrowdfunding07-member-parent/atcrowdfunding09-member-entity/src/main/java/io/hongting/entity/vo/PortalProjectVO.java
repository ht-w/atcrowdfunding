package io.hongting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongting
 * @create 2021 10 21 1:58 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO {
    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployDate;
    private Integer percentage;
    private Integer supporter;
}