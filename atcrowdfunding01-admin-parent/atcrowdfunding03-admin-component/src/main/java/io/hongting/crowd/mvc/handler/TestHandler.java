package io.hongting.crowd.mvc.handler;

import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hongting
 * @create 2021 09 26 5:59 PM
 */

@Controller
public class TestHandler {

    @Autowired
    AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSSM(Model model){
        List<Admin> list = adminService.getAll();
        model.addAttribute("list", list);

        System.out.println(10 / 0);
        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testReceiveArray(@RequestParam("array[]") List<Integer> array){
        for (Integer number: array){
            System.out.println(number);
        }

        return "success";
    }


    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArray2(@RequestBody List<Integer> array){
        for (Integer number: array){
            System.out.println(number);
        }

        return "success";
    }
}
