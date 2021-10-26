package io.hongting.service.impl;

import io.hongting.entity.po.AddressPO;
import io.hongting.entity.po.AddressPOExample;
import io.hongting.entity.po.OrderPO;
import io.hongting.entity.po.OrderProjectPO;
import io.hongting.entity.vo.AddressVO;
import io.hongting.entity.vo.OrderProjectVO;
import io.hongting.entity.vo.OrderVO;
import io.hongting.mapper.AddressPOMapper;
import io.hongting.mapper.MemberPOMapper;
import io.hongting.mapper.OrderPOMapper;
import io.hongting.mapper.OrderProjectPOMapper;
import io.hongting.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hongting
 * @create 2021 10 24 2:59 PM
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    AddressPOMapper addressPOMapper;

    @Autowired
    OrderPOMapper orderPOMapper;


    @Override
    public OrderProjectVO getOrderProjectVO(Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAddressVO(Integer memberId) {
        AddressPOExample addressPOExample = new AddressPOExample();
        AddressPOExample.Criteria criteria = addressPOExample.createCriteria();
        criteria.andIdEqualTo(memberId);
        List<AddressPO> addressPOS = addressPOMapper.selectByExample(addressPOExample);
        return  addressPOS.stream().map(addressPO -> {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO, addressVO);
            return addressVO;
        }).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveAddressPO(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO, addressPO);
        addressPOMapper.insert(addressPO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO, orderPO);
        orderPOMapper.insert(orderPO);
        Integer orderId = orderPO.getId();
        OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        BeanUtils.copyProperties(orderProjectVO, orderProjectPO);
        orderProjectPO.setOrderId(orderId);
        orderProjectPOMapper.insert(orderProjectPO);
    }
}
