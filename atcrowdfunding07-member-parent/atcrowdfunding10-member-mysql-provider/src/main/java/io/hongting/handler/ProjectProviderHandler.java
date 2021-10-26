package io.hongting.handler;

import io.hongting.crowd.util.R;
import io.hongting.entity.vo.DetailProjectVO;
import io.hongting.entity.vo.OrderVO;
import io.hongting.entity.vo.PortalTypeVO;
import io.hongting.entity.vo.ProjectVO;
import io.hongting.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 20 8:10 PM
 */


@RestController
public class ProjectProviderHandler {

    @Autowired
    private ProjectService projectService;


    @RequestMapping("/save/project/remote")
    R<String> saveProject(@RequestBody ProjectVO projectVO, @RequestParam("memberid") Integer id){
        try {
            projectService.saveProject(projectVO,id);
            return R.success();
        } catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/portal/type/project/data/remote")
    public R<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){
        try {
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVO();
            return R.success(portalTypeVOList);
        } catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/detail/project/remote/{projectId}")
    public R<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId){
        try {
            return R.success(projectService.getDetailProjectVO(projectId));
        } catch (Exception e) {
            e.printStackTrace();
            return  R.fail(e.getMessage());
        }
    }



}
