package io.hongting.handler;

import io.hongting.api.MySQLRemoteService;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.util.R;
import io.hongting.entity.vo.PortalTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 14 3:27 PM
 */
@Controller
public class PortalHandler {


    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(Model model){
        R<List<PortalTypeVO>> r = mySQLRemoteService.getPortalTypeProjectDataRemote();
        if (R.SUCCESS.equals(r.getResult())){
            List<PortalTypeVO> portalTypeVOList = r.getData();
            model.addAttribute(ResultConstant.ATTR_NAME_PORTAL_TYPE_LIST,portalTypeVOList);
        }

        return "portal";
    }

}