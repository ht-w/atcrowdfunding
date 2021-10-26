package io.hongting.handler;

import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.util.R;
import io.hongting.entity.po.MemberPO;
import io.hongting.entity.vo.ProjectVO;
import io.hongting.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongting
 * @create 2021 10 14 2:59 PM
 */

@RestController
public class MemberProviderHandler {

    @Autowired
    MemberService memberService;

    @RequestMapping("/get/member/by/login/acct/remote")
    public R<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return R.success(memberPO);
        } catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/member/remote")
    public R<String> saveMember (@RequestBody  MemberPO memberPO){
        try {
            memberService.saveMember(memberPO);
            return R.success();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                return R.fail(ResultConstant.MESSAGE_LOGIN_NOTUNIQUE);
            }
            return R.fail(e.getMessage());
        }
    }

}
