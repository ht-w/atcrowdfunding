package io.hongting.crowd.service.impl;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import io.hongting.crowd.entity.Menu;
import io.hongting.crowd.entity.MenuExample;
import io.hongting.crowd.mapper.MenuMapper;
import io.hongting.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hongting
 * @create 2021 10 03 2:25 PM
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getWholeTree() {
        List<Menu> menus = menuMapper.selectByExample(new MenuExample());

        List<Menu> level1Menu = menus.stream().filter(menu ->
            menu.getPid() == null
        ).map(menu->{
           menu.setChildren(getChildren(menu, menus));
           return menu;
        }).collect(Collectors.toList());

        return level1Menu;
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void removeMenuById(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }


    private List<Menu> getChildren(Menu root, List<Menu> menus){

        List<Menu> children = menus.stream().filter(menu ->
            menu.getPid() == root.getId()
        ).map(menu ->{
            menu.setChildren(getChildren(menu,menus));
            return menu;
        }).collect(Collectors.toList());

        return children;
    }
}
