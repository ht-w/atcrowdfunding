package io.hongting.service.api;

import io.hongting.entity.vo.AddressVO;
import io.hongting.entity.vo.OrderProjectVO;
import io.hongting.entity.vo.OrderVO;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 2:58 PM
 */
public interface OrderService {

    OrderProjectVO getOrderProjectVO(Integer returnId);

    List<AddressVO> getAddressVO(Integer memberId);

    void saveAddressPO(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
