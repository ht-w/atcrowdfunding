package io.hongting.crowd.service.api;

import com.github.pagehelper.PageInfo;
import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.Role;

import java.util.List;

/**
 * @author hongting
 * @create 2021 09 30 10:50 AM
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void saveRole(Role role);

    void edit(Role role);

    void remove(List<Integer> roleIds);

    List<Role> getAssignedRole(Integer id);

    List<Role> getUnassignedRole(Integer id);
}
