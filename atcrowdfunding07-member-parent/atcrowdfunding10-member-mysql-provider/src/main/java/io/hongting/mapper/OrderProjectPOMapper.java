package io.hongting.mapper;

import io.hongting.entity.po.OrderProjectPO;
import io.hongting.entity.po.OrderProjectPOExample;
import io.hongting.entity.vo.OrderProjectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 3:41 PM
 */
@Component
public interface OrderProjectPOMapper {

    int countByExample(OrderProjectPOExample example);

    int deleteByExample(OrderProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProjectPO record);

    int insertSelective(OrderProjectPO record);

    List<OrderProjectPO> selectByExample(OrderProjectPOExample example);

    OrderProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByExample(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByPrimaryKeySelective(OrderProjectPO record);

    int updateByPrimaryKey(OrderProjectPO record);

    OrderProjectVO selectOrderProjectVO(@Param ("returnId") Integer returnId);
}
