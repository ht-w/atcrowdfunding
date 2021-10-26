package io.hongting.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.AdminExample;
import io.hongting.crowd.mapper.AdminMapper;
import io.hongting.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hongting
 * @create 2021 09 27 3:46 PM
 */


@Controller
public class AdminHandler {


    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/login.html")
    public String login(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPwsd") String userPwsd, HttpSession sesssion){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPwsd);
        sesssion.setAttribute(ResultConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/main/page.html";

    }

    @RequestMapping("/admin/logout.html")
    public String logout (HttpSession sesssion){
        sesssion.invalidate();
        return "redirect:/admin/login/page.html";
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, Model model){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        model.addAttribute(ResultConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    /**
     * remove admin
     * @param id
     * @return
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(@PathVariable("adminId") Integer id , @PathVariable("pageNum") Integer pageNum, @PathVariable("keyword") String keyword, HttpServletRequest request){
        adminService.removeAdmin(id, request);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @PreAuthorize("hasAuthority('user:save')")
    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }


    @RequestMapping("/admin/update/{adminId}/{pageNum}/{keyword}.html")
    public String getUpdateInfo(@PathVariable("adminId") Integer id , @PathVariable("pageNum") Integer pageNum, @PathVariable("keyword") String keyword, Model model) {
        Admin admin = adminService.queryAdmin(id);
        model.addAttribute("admin", admin);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("keyword", keyword);
        return "admin-edit";
    }

    @RequestMapping("/admin/edit.html")
    public String updateAdmin(Admin admin,@RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword){
        adminService.updateAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    
}