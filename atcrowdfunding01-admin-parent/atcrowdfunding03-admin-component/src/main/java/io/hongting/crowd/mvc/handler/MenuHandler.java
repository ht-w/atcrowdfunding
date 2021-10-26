package io.hongting.crowd.mvc.handler;

import io.hongting.crowd.entity.Menu;
import io.hongting.crowd.service.api.MenuService;
import io.hongting.crowd.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 03 2:25 PM
 */


@RestController
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/get.json")
    public R<Menu> getWholeTree() {
        List<Menu> list = menuService.getWholeTree();
        Menu root = list.get(0);
        return R.success(root);
    }


    @RequestMapping("/menu/save.json")
    public R<String> saveMenu(Menu menu) {
        menuService.saveMenu(menu);
        return R.success();
    }

    @RequestMapping("/menu/remove.json")
    public R<String> removeMenuById(Integer id) {
        menuService.removeMenuById(id);
        return R.success();

    }


    @RequestMapping("/menu/edit.json")
    public R<String> eidtMenu(Menu menu){
        menuService.editMenu(menu);
        return R.success();

    }
}
