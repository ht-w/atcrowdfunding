package io.hongting.crowd.mvc.handler;

import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.Auth;
import io.hongting.crowd.entity.Role;
import io.hongting.crowd.mapper.AuthMapper;
import io.hongting.crowd.service.api.AdminService;
import io.hongting.crowd.service.api.AuthService;
import io.hongting.crowd.service.api.RoleService;
import io.hongting.crowd.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 10 05 12:01 PM
 */
@Controller
public class AssignHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/assign/page/{adminId}/{pageNum}/{keyword}.html")
    public String toAssignRolePage (@PathVariable("adminId") Integer id , @PathVariable("pageNum") Integer pageNum, @PathVariable("keyword") String keyword, Model model){
        List<Role> assignedRoles = roleService.getAssignedRole(id);
        List<Role> unassignedRoles = roleService.getUnassignedRole(id);

        model.addAttribute("assignedRoles", assignedRoles);
        model.addAttribute("adminId", id);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("unassignedRoles", unassignedRoles);

        return "assign-role";
    }


    @RequestMapping("/assign/assignRole.html")
    public String saveAdminRoleRelationship(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword, @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList){
        adminService.saveAdminRoleRelationship(adminId, roleIdList);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @ResponseBody
    @RequestMapping("/assign/get/tree.json")
    public R<List<Auth>> getAuthTree(){
        List<Auth> list = authService.getAuthList();
        return R.success(list);
    }


    @ResponseBody
    @RequestMapping("/assign/get/checked/auth/id.json")
    public R<List<Integer>> getAuthByRoleId(Integer roleId){
        List<Integer> authIdList = authService.getAuthByRoleId(roleId);
        return R.success(authIdList);
    }


    @ResponseBody
    @RequestMapping("/assign/save/role/auth/relationship.json")
    public R<String> saveRoleAuthRelationship(
            // 用一个map接收前端发来的数据
            @RequestBody Map<String,List<Integer>> map ) {
        // 保存更改后的Role与Auth关系
        authService.saveRoleAuthRelationship(map);

        return R.success();
    }
}
