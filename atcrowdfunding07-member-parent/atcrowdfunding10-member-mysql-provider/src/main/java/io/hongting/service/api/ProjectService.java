package io.hongting.service.api;

import io.hongting.entity.vo.DetailProjectVO;
import io.hongting.entity.vo.PortalTypeVO;
import io.hongting.entity.vo.ProjectVO;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 20 8:11 PM
 */
public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer id);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}
