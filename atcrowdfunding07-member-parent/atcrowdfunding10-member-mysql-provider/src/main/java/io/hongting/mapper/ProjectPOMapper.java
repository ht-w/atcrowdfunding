package io.hongting.mapper;

import io.hongting.entity.po.ProjectPO;
import io.hongting.entity.po.ProjectPOExample;
import java.util.List;

import io.hongting.entity.vo.DetailProjectVO;
import io.hongting.entity.vo.PortalTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ProjectPOMapper {
    long countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

    void saveTypeRelationship(@Param("projectId")Integer projectId, @Param("typeIdList") List<Integer> typeIdList);

    void saveTagRelationship(@Param("projectId")Integer projectId,@Param("tagIdList") List<Integer> tagIdList);

    List<PortalTypeVO> selectPortalTypeVOList();

    DetailProjectVO selectDetailProjectVO(Integer projectId);
}