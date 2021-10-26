package io.hongting.service.api;

import io.hongting.entity.po.MemberPO;
import io.hongting.entity.vo.ProjectVO;

/**
 * @author hongting
 * @create 2021 10 14 3:00 PM
 */
public interface MemberService {

    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);


}
