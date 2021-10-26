package io.hongting.crowd.service.impl;

import io.hongting.crowd.entity.Auth;
import io.hongting.crowd.entity.AuthExample;
import io.hongting.crowd.mapper.AuthMapper;
import io.hongting.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 10 05 2:37 PM
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAuthList() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAuthByRoleId(Integer roleId) {
        return  authMapper.getAuthByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        authMapper.clearRelationship(roleId);
        List<Integer> authIdList = map.get ("authIdList");
        if(authIdList!=null && !authIdList.isEmpty()){
                authMapper.saveRoleAuthRelationship(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAuthNameByAdminId(adminId);
    }
}
