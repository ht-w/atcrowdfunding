package io.hongting.service.impl;

import io.hongting.entity.po.MemberConfirmInfoPO;
import io.hongting.entity.po.MemberLaunchInfoPO;
import io.hongting.entity.po.ProjectPO;
import io.hongting.entity.po.ReturnPO;
import io.hongting.entity.vo.*;
import io.hongting.mapper.*;
import io.hongting.service.api.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hongting
 * @create 2021 10 20 8:11 PM
 */


@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {



    @Autowired
    ProjectPOMapper projectPOMapper;

    @Autowired
    MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

    @Autowired
    ReturnPOMapper returnPOMapper;

    @Autowired
    ProjectItemPicPOMapper projectItemPicPOMapper;

    @Autowired
    MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveProject(ProjectVO projectVO, Integer id) {
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(projectVO, projectPO);
        projectPO.setMemberid(id);
        String createDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        projectPO.setCreatedate(createDate);
        projectPO.setStatus(0);
        projectPOMapper.insertSelective(projectPO);
        Integer projectId = projectPO.getId();


        //Save TypePO
        List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.saveTypeRelationship(projectId, typeIdList);


        //SaveTagPO
        List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.saveTagRelationship(projectId, tagIdList);

        //save ProjectItemPicPOMapper
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemPicPOMapper.insertPathList(projectId, detailPicturePathList);

        //save MemberLaunchInfoPO
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        MemberLaunchInfoVO memberLaunchInfoVO = projectVO.getMemberLaunchInfoVO();
        BeanUtils.copyProperties(memberLaunchInfoVO,memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberid(id);
        memberLaunchInfoPOMapper.insertSelective(memberLaunchInfoPO);

        //save ReturnPO

        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        List<ReturnPO> list = new ArrayList<>();
        for (ReturnVO returnVO : returnVOList) {
            ReturnPO returnPO = new ReturnPO();
            BeanUtils.copyProperties(returnVO, returnPO);
            list.add(returnPO);
        }
        returnPOMapper.insertReturnPOList(projectId,list);

        //Save MemberConfirmInfoPO
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO,memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberid(id);
        memberConfirmInfoPOMapper.insertSelective(memberConfirmInfoPO);


    }

    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return projectPOMapper.selectPortalTypeVOList();
    }

    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {
        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);
        Integer status = detailProjectVO.getStatus();
        switch (status){
            case 0:
                detailProjectVO.setStatusText("即将开始");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
                break;
            case 2:
                detailProjectVO.setStatusText("众筹成功");
                break;
            case 3:
                detailProjectVO.setStatusText("众筹失败");
                break;
            default:
                break;
        }

        String deployDate = detailProjectVO.getDeployDate();
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse(deployDate);
            long currentDateTime = currentDate.getTime();
            long deployDateTime = parse.getTime();

            long pastday = (currentDateTime - deployDateTime)/1000/60/60/24;

            Integer totalday = detailProjectVO.getDay();

            Integer lastDay = (int)(totalday - pastday);

            detailProjectVO.setLastDay(lastDay);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  detailProjectVO;

    }
}
