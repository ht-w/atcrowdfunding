package io.hongting.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.Role;
import io.hongting.crowd.service.api.RoleService;
import io.hongting.crowd.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hongting
 * @create 2021 09 30 10:54 AM
 */

@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping("/role/page/info.json")
    public R<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword ) {

        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);

        return R.success(pageInfo);
    }


    @ResponseBody
    @RequestMapping("/role/save.json")
    public R<Role> saveRole( Role role ){
        roleService.saveRole(role);
        return R.success(role);
    }


    @ResponseBody
    @RequestMapping("/role/edit.json")
    public R<String> editRole(Role role){
        roleService.edit(role);
        return R.success();
    }


    @ResponseBody
    @RequestMapping("/role/remove.json")
    public R<String> removeRole(@RequestBody List<Integer> roleIds){
        roleService.remove(roleIds);
        return R.success();
    }
}
