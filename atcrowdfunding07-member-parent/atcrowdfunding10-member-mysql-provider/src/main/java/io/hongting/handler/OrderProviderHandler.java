package io.hongting.handler;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.hongting.crowd.util.R;
import io.hongting.entity.vo.AddressVO;
import io.hongting.entity.vo.OrderProjectVO;
import io.hongting.entity.vo.OrderVO;
import io.hongting.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 2:57 PM
 */
@RestController
public class OrderProviderHandler {

    @Autowired
    OrderService orderService;


    @RequestMapping("/get/order/project/vo/remote")
    public R<OrderProjectVO> getOrderProjectVO(@RequestParam("returnId") Integer returnId) {

        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(returnId);
            return R.success(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }



    @RequestMapping("/get/order/address/remote")
     public R<List<AddressVO>> getAddressListByMemberIdRemote(@RequestParam ("memberId") Integer memberId){
        try {
            List<AddressVO> list = orderService.getAddressVO(memberId);
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }


    @RequestMapping("/save/address/remote")
    R<String> saveAddressRemote(@RequestBody AddressVO addressVO) {
        try {
            orderService.saveAddressPO(addressVO);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }


    @RequestMapping("/save/order/remote")
    R<String> saveOrderRemote(@RequestBody OrderVO orderVO) {
        try {
            orderService.saveOrder(orderVO);
            return R.success();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }
}
