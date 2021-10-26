package io.hongting.crowd.service.api;

import io.hongting.crowd.entity.Menu;

import java.util.List;

/**
 * @author hongting
 * @create 2021 10 03 2:24 PM
 */
public interface MenuService {



    List<Menu> getWholeTree();

    void saveMenu(Menu menu);

    void removeMenuById(Integer id);

    void editMenu(Menu menu);
}
