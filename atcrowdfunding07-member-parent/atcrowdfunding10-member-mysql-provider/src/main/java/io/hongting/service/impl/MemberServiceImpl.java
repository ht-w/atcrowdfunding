package io.hongting.service.impl;

import io.hongting.entity.po.MemberPO;
import io.hongting.entity.po.MemberPOExample;
import io.hongting.entity.po.ProjectPO;
import io.hongting.entity.vo.ProjectVO;
import io.hongting.mapper.MemberPOMapper;
import io.hongting.mapper.ProjectPOMapper;
import io.hongting.service.api.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author hongting
 * @create 2021 10 14 3:00 PM
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberPOMapper memberPOMapper;


    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        MemberPOExample memberPOExample = new MemberPOExample();
        MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginacct);
        List<MemberPO> memberPOList = memberPOMapper.selectByExample(memberPOExample);
        if (memberPOList!=null && !memberPOList.isEmpty()){
            return memberPOList.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class,readOnly = false)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }


}
