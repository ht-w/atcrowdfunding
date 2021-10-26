package io.hongting.mapper;

import io.hongting.entity.po.AddressPO;
import io.hongting.entity.po.AddressPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 3:40 PM
 */

@Component
public interface AddressPOMapper {

    int countByExample(AddressPOExample example);

    int deleteByExample(AddressPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AddressPO record);

    int insertSelective(AddressPO record);

    List<AddressPO> selectByExample(AddressPOExample example);

    AddressPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AddressPO record, @Param("example") AddressPOExample example);

    int updateByExample(@Param("record") AddressPO record, @Param("example") AddressPOExample example);

    int updateByPrimaryKeySelective(AddressPO record);

    int updateByPrimaryKey(AddressPO record);
}