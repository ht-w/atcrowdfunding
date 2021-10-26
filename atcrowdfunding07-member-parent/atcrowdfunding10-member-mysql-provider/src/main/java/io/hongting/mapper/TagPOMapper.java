package io.hongting.mapper;

import io.hongting.entity.po.TagPO;
import io.hongting.entity.po.TagPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface TagPOMapper {
    long countByExample(TagPOExample example);

    int deleteByExample(TagPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagPO record);

    int insertSelective(TagPO record);

    List<TagPO> selectByExample(TagPOExample example);

    TagPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagPO record, @Param("example") TagPOExample example);

    int updateByExample(@Param("record") TagPO record, @Param("example") TagPOExample example);

    int updateByPrimaryKeySelective(TagPO record);

    int updateByPrimaryKey(TagPO record);
}