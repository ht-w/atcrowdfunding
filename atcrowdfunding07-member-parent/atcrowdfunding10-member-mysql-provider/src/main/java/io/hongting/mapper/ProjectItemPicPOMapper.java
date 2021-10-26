package io.hongting.mapper;

import io.hongting.entity.po.ProjectItemPicPO;
import io.hongting.entity.po.ProjectItemPicPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ProjectItemPicPOMapper {
    long countByExample(ProjectItemPicPOExample example);

    int deleteByExample(ProjectItemPicPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectItemPicPO record);

    int insertSelective(ProjectItemPicPO record);

    List<ProjectItemPicPO> selectByExample(ProjectItemPicPOExample example);

    ProjectItemPicPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectItemPicPO record, @Param("example") ProjectItemPicPOExample example);

    int updateByExample(@Param("record") ProjectItemPicPO record, @Param("example") ProjectItemPicPOExample example);

    int updateByPrimaryKeySelective(ProjectItemPicPO record);

    int updateByPrimaryKey(ProjectItemPicPO record);

    void insertPathList(@Param("projectId") Integer projectId,  @Param ("detailPicturePathList") List<String> detailPicturePathList);
}