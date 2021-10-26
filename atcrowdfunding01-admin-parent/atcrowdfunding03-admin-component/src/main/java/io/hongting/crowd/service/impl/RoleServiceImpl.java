package io.hongting.crowd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.hongting.crowd.entity.Role;
import io.hongting.crowd.entity.RoleExample;
import io.hongting.crowd.mapper.RoleMapper;
import io.hongting.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongting
 * @create 2021 09 30 10:51 AM
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void edit(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void remove(List<Integer> roleIds) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIds);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRole(Integer id) {
        return roleMapper.selectAssignedRole(id);
    }

    @Override
    public List<Role> getUnassignedRole(Integer id) {
        return roleMapper.selectUnassignedRole(id);
    }
}
