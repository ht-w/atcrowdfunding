package io.hongting.handler;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.hongting.api.MySQLRemoteService;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.entity.vo.OrderProjectVO;
import io.hongting.entity.vo.OrderVO;
import io.hongting.enums.PaypalPaymentIntent;
import io.hongting.enums.PaypalPaymentMethod;
import io.hongting.service.PayPalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author hongting
 * @create 2021 10 26 3:17 PM
 */

@Controller
public class PayHandler {

    @Autowired
    PayPalService payPalService;

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    private Logger log = LoggerFactory.getLogger(getClass());

//    @RequestMapping("/generate/order")
//    public String generateOrder(OrderVO orderVO, HttpSession httpSession, HttpServletRequest httpServletRequest){
//         OrderProjectVO orderProjectVO= (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);
//         orderVO.setOrderProjectVO(orderProjectVO);
//
//         //generate order number
//        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
//        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String orderNum = uuid+time;
//        orderVO.setOrderNum(orderNum);
//        Double orderAmount = (double)orderProjectVO.getSupportPrice()*orderProjectVO.getFreight()+orderProjectVO.getReturnCount();
//        orderVO.setOrderAmount(orderAmount);
//        httpSession.setAttribute(ResultConstant.ATTR_NAME_ORDERVO, orderVO);
//        return pay(httpServletRequest, httpSession);
//
//    }


    @RequestMapping("/generate/order")
    public String pay(HttpServletRequest request, HttpSession httpSession, OrderVO orderVO){
        OrderProjectVO orderProjectVO= (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);
        orderVO.setOrderProjectVO(orderProjectVO);

        //generate order number
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String orderNum = uuid+time;
        orderVO.setOrderNum(orderNum);
        Double orderAmount = (double)(orderProjectVO.getReturnCount() * orderProjectVO.getSupportPrice() + orderProjectVO.getFreight());
        orderVO.setOrderAmount(orderAmount);
        httpSession.setAttribute(ResultConstant.ATTR_NAME_ORDERVO, orderVO);
        OrderVO orderVO2 = (OrderVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERVO);
        String successUrl = "http://localhost/pay/success";
        String cancelUrl = "http://localhost/pay/cancel";
        try{
            Payment payment = payPalService.createPayment(orderVO2.getOrderAmount(), "MYR", PaypalPaymentMethod.paypal, PaypalPaymentIntent.sale ,orderVO2.getInvoiceTitle(),cancelUrl,successUrl );
            for (Links links: payment.getLinks()){
                if (links.getRel().equals("approval_url")){
                    return"redirect:" + links.getHref();
                }
            }
        }catch (PayPalRESTException e){
            log.error(e.getMessage());
        }
        return "redirect:http://localhost/auth/member/center/page.html";
    }


    @RequestMapping ("/cancel")
    public String cancelPay(HttpSession httpSession){
        OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);
        Integer returnCount = orderProjectVO.getReturnCount();
        return "redirect:http://localhost/order/confirm/order/" + returnCount;
    }


    @RequestMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession httpSession){
        try{
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                OrderVO orderVO = (OrderVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERVO);
                mySQLRemoteService.saveOrderRemote(orderVO);
            }
        }catch (PayPalRESTException e) {
            log.error(e.getMessage());
            OrderProjectVO orderProjectVO = (OrderProjectVO) httpSession.getAttribute(ResultConstant.ATTR_NAME_ORDERPROJECTVO);
            return "redirect:http://localhost/order/confirm/order/" + orderProjectVO.getReturnCount();
        }
        return "success";
    }

}
