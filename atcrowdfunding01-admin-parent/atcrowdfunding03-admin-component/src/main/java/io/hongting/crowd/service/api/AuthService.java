package io.hongting.crowd.service.api;

import io.hongting.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 10 05 2:36 PM
 */
public interface AuthService {


    List<Auth> getAuthList();

    List<Integer> getAuthByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String,List<Integer>> map);

    List<String> getAuthNameByAdminId(Integer adminId);
}
