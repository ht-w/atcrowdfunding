package io.hongting.api;

import io.hongting.crowd.util.R;
import io.hongting.entity.po.MemberPO;
import io.hongting.entity.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 14 2:57 PM
 */
@FeignClient("crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping("/get/member/by/login/acct/remote")
    R<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping("/save/member/remote")
    R<String> saveMember (@RequestBody MemberPO memberPO);

    @RequestMapping("/save/project/remote")
    R<String> saveProject(@RequestBody ProjectVO projectVO, @RequestParam("memberid") Integer id);

    @RequestMapping("/get/portal/type/project/data/remote")
    R<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping("/get/detail/project/remote/{projectId}")
    R<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

    @RequestMapping("/get/order/project/vo/remote")
    R<OrderProjectVO> getOrderProjectVO(@RequestParam("returnId") Integer returnId);

    @RequestMapping("/get/order/address/remote")
    R<List<AddressVO>> getAddressListByMemberIdRemote(@RequestParam ("memberId") Integer memberId);

    @RequestMapping("/save/address/remote")
    R <String> saveAddressRemote(@RequestBody AddressVO addressVO);

    @RequestMapping("/save/order/remote")
    R<String> saveOrderRemote(@RequestBody  OrderVO orderVO);
}
