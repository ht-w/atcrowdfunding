package io.hongting.handler;

import io.hongting.api.MySQLRemoteService;
import io.hongting.config.OssProperties;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.util.R;
import io.hongting.crowd.util.Util;
import io.hongting.entity.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongting
 * @create 2021 10 20 3:00 PM
 */

@Controller
public class ProjectHandler {

    @Autowired
    private OssProperties ossProperties;

    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/create/project/information")
    public String createProject(ProjectVO projectVO, @RequestParam ("headerPicture") MultipartFile headerPic, @RequestParam("detailPictureList") List<MultipartFile> detailPictureList, HttpSession httpSession, Model model) throws IOException {
        boolean empty = headerPic.isEmpty();
        if (empty){
            model.addAttribute("message", ResultConstant.MESSAGE_HEADER_PIC_EMPTY);
            return "launch";
        }
        R<String> r = Util.uploadFileToOSS(ossProperties.getEndpoint(), ossProperties.getKeyId(), ossProperties.getKeySecret(), headerPic.getInputStream(), ossProperties.getBucketName(), ossProperties.getBucketDomain(), headerPic.getOriginalFilename());

        if (r.getResult().equals(R.FAIL)){
            model.addAttribute("message", ResultConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
            return "launch";
        }else{
            String data = r.getData();
            projectVO.setHeaderPicturePath(data);
        }


        if(detailPictureList==null || detailPictureList.isEmpty()){
            model.addAttribute("message", ResultConstant.MESSAGE_DETAIL_PIC_EMPTY);
            return "launch";
        }
        List<String> list = new ArrayList<>();

        for (MultipartFile multipartFile : detailPictureList) {
            R<String> r1 = Util.uploadFileToOSS(ossProperties.getEndpoint(), ossProperties.getKeyId(), ossProperties.getKeySecret(), multipartFile.getInputStream(), ossProperties.getBucketName(), ossProperties.getBucketDomain(), multipartFile.getOriginalFilename());
            if (r1.getResult().equals(R.FAIL)){
                model.addAttribute("message", ResultConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
                return "launch";
            }else{
                String data = r1.getData();
                list.add(data);
            }
        }
        projectVO.setDetailPicturePathList(list);
        httpSession.setAttribute(ResultConstant.ATTR_NAME_PROJECT, projectVO);
        return "redirect:http://localhost/project/info/page.html";

    }

    @ResponseBody
    @RequestMapping("/upload/return/picture.json")
    public R<String> uploadReturnPicture(@RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
        boolean empty = returnPicture.isEmpty();
        if (empty){
           return R.fail(ResultConstant.MESSAGE_RETURN_PIC_EMPTY);
        }
        return  Util.uploadFileToOSS(ossProperties.getEndpoint(), ossProperties.getKeyId(), ossProperties.getKeySecret(), returnPicture.getInputStream(), ossProperties.getBucketName(), ossProperties.getBucketDomain(), returnPicture.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/save/return.json")
    public R<String> saveReturn (ReturnVO returnVO, HttpSession httpSession){
        try {
            ProjectVO projectVO = (ProjectVO)httpSession.getAttribute(ResultConstant.ATTR_NAME_PROJECT);
            if(projectVO==null){
                return R.fail(ResultConstant.MESSAGE_PROJECT_MISSING);
            }
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            if (returnVOList==null || returnVOList.isEmpty()){
                returnVOList = new ArrayList<>();
                projectVO.setReturnVOList(returnVOList);
            }
            returnVOList.add(returnVO);
            httpSession.setAttribute(ResultConstant.ATTR_NAME_PROJECT, projectVO);
            return R.success();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @RequestMapping("/create/confirm.html")
    public String confirmSave(HttpSession httpSession, MemberConfirmInfoVO memberConfirmInfoVO, Model model){
        ProjectVO projectVO = (ProjectVO)httpSession.getAttribute(ResultConstant.ATTR_NAME_PROJECT);
        if(projectVO==null){
            throw new RuntimeException(ResultConstant.MESSAGE_PROJECT_MISSING);
        }
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        MemberLoginVo memberLoginVo = (MemberLoginVo) httpSession.getAttribute(ResultConstant.ATTR_NAME_LOGIN_MEMBER);
        Integer id = memberLoginVo.getId();

        R <String>  r = mySQLRemoteService.saveProject(projectVO, id);

        if(r.getResult().equals(R.FAIL)){
            model.addAttribute("message", r.getMessage());
            return "confirm";
        }

        httpSession.removeAttribute(ResultConstant.ATTR_NAME_PROJECT);

        return "redirect:http://localhost/project/success/page.html";

    }


    @RequestMapping("/show/project/detail/{projectId}")
    public String getProjectDetail (@PathVariable ("projectId") Integer projectId, Model model){
        R<DetailProjectVO> r = mySQLRemoteService.getDetailProjectVORemote(projectId);
        if (r.getResult().equals(R.SUCCESS)){
            DetailProjectVO data = r.getData();
            model.addAttribute(ResultConstant.ATTR_NAME_DETAILPROJECT, data);
        }
        return "detail";

    }
}
