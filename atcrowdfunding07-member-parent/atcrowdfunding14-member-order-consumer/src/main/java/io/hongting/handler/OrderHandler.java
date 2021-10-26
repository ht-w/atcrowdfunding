package io.hongting.handler;

import io.hongting.api.MySQLRemoteService;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.util.R;
import io.hongting.entity.vo.AddressVO;
import io.hongting.entity.vo.MemberLoginVo;
import io.hongting.entity.vo.OrderProjectVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hongting
 * @create 2021 10 24 2:52 PM
 */

@Controller
public class OrderHandler {

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/confirm/return/info/{returnId}")
    public String confirmReturnInfo(@PathVariable("returnId") Integer returnId, HttpSession session) {

        R<OrderProjectVO> r = mySQLRemoteService.getOrderProjectVO(returnId);

        if (R.SUCCESS.equals(r.getResult())){
            session.setAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO, r.getData());
        }

        return "order-confirm-return";
    }



    @RequestMapping("/confirm/order/{returnCount}")
    public String confirmOrder(@PathVariable ("returnCount") Integer returnCount, HttpSession httpSession){
        OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);
        orderProjectVO.setReturnCount(returnCount);
        httpSession.setAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO, orderProjectVO);
        MemberLoginVo memberLoginVo = (MemberLoginVo) httpSession.getAttribute(ResultConstant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = memberLoginVo.getId();
        R<List<AddressVO>> r = mySQLRemoteService.getAddressListByMemberIdRemote(memberId);

        if (R.SUCCESS.equals(r.getResult())){
            List<AddressVO> list = r.getData();
            httpSession.setAttribute(ResultConstant.ATTR_NAME_ADDRESSVO, list);
        }

        return "order-confirm-order";
    }

    @RequestMapping("/save/address")
    public String saveAddress(AddressVO addressVO, HttpSession httpSession){
        R<String>r = mySQLRemoteService.saveAddressRemote(addressVO);

        OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);

        Integer returnCount = orderProjectVO.getReturnCount();

        return "redirect:http://localhost/order/confirm/order/" + returnCount;
    }

}
