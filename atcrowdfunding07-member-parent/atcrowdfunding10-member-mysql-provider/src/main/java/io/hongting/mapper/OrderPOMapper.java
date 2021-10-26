package io.hongting.mapper;

import io.hongting.entity.po.OrderPO;
import io.hongting.entity.po.OrderPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 3:41 PM
 */

@Component
public interface OrderPOMapper {
    int countByExample(OrderPOExample example);

    int deleteByExample(OrderPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    List<OrderPO> selectByExample(OrderPOExample example);

    OrderPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderPO record, @Param("example") OrderPOExample example);

    int updateByExample(@Param("record") OrderPO record, @Param("example") OrderPOExample example);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);
}