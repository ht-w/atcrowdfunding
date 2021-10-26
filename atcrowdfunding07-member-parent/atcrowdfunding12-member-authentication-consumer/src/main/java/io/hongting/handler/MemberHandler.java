package io.hongting.handler;

import io.hongting.api.MySQLRemoteService;
import io.hongting.api.RedisRemoteService;
import io.hongting.config.ShortMessageProperties;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.util.R;
import io.hongting.crowd.util.Util;
import io.hongting.entity.po.MemberPO;
import io.hongting.entity.vo.MemberLoginVo;
import io.hongting.entity.vo.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author hongting
 * @create 2021 10 17 2:28 PM
 */

@Controller
public class MemberHandler {

    @Autowired
    ShortMessageProperties shortMessageProperties;

    @Autowired
    RedisRemoteService redisRemoteService;

    @Autowired
    MySQLRemoteService mySQLRemoteService;


    @ResponseBody
    @RequestMapping("/auth/member/sendMessage.json")
    public R<String> sendMessage(@RequestParam ("phoneNum") String phone){
        R<String> result = Util.sendCodeByShortMessage(shortMessageProperties.getApiKey(), shortMessageProperties.getApiSecret(), phone, shortMessageProperties.getHost());


        if (R.SUCCESS.equals(result.getResult())){
            String key = ResultConstant.REDIS_CODE_PREFIX + phone;
            String code = result.getData();
            R<String> result2  = redisRemoteService.setRedisKeyValueWithTimeoutRemote(key, code, 15, TimeUnit.MINUTES);
            if (R.SUCCESS.equals(result2.getResult())){
                return R.success();
            }else {
                return result2;
            }
        }else{
            return result;
        }
    }


    @RequestMapping("/auth/member/register.html")
    public String registerMember (MemberVO memberVO, Model model){
        String phoneNum = memberVO.getPhoneNum();
        String code = memberVO.getCode();
        String redisKey = ResultConstant.REDIS_CODE_PREFIX + phoneNum;
        R r = redisRemoteService.getRedisValueByKeyRemote(redisKey);
        String result = r.getResult();
        if (result.equals(R.FAIL)){
           model.addAttribute("message", r.getMessage());
           return "member-reg";
        }
        String redisCode  = (String)r.getData();
        if(redisCode == null){
            model.addAttribute("message", ResultConstant.MESSAGE_CODE_NOT_EXIST);
            return "member-reg";
        }
        if (!redisCode.equals(code)){
            model.addAttribute("message", ResultConstant.MESSAGE_CODE_INVALID);
            return "member-reg";
        }

        redisRemoteService.RemoveRedisKeyByKeyRemote(redisKey);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPswd = bCryptPasswordEncoder.encode(memberVO.getUserPswd());
        memberVO.setUserPswd(encodedPswd);

        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO, memberPO);
        R<String> r2 = mySQLRemoteService.saveMember(memberPO);
        if(r2.getResult().equals(R.FAIL)){
            model.addAttribute("message", r2.getMessage());
            return "member-reg";
        }

        return "redirect:http://localhost/auth/member/login/page.html";

    }


    @RequestMapping("/auth/member/login.html")
    public String login (@RequestParam ("loginAcct") String loginAcct, @RequestParam("loginPswd") String loginPswd, Model model, HttpSession httpSession){
        R<MemberPO> r = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginAcct);
        if (r.getResult().equals(R.FAIL)){
            model.addAttribute("message", r.getMessage());
            return "login";
        }
        MemberPO memberPO = r.getData();

        if (memberPO==null){
            model.addAttribute("message", ResultConstant.MESSAGE_LOGIN_FAILED);
        }

        String userPswd = memberPO.getUserPswd();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(loginPswd, userPswd);
        if (!matches){
            model.addAttribute("message", ResultConstant.MESSAGE_LOGIN_FAILED);
            return "login";
        }
        MemberLoginVo memberLoginVo = new MemberLoginVo(memberPO.getId(), memberPO.getUserName(), memberPO.getEmail());
        httpSession.setAttribute(ResultConstant.ATTR_NAME_LOGIN_MEMBER, memberLoginVo);
        return "redirect:http://localhost/auth/member/center/page.html";


    }

    @RequestMapping("/auth/member/logout.html")
    public String logout (HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }



}
