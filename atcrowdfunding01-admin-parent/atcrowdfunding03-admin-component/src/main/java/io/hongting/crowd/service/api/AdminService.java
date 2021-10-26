package io.hongting.crowd.service.api;

import com.github.pagehelper.PageInfo;
import io.hongting.crowd.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hongting
 * @create 2021 09 26 4:09 PM
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPwsd);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void removeAdmin(Integer id, HttpServletRequest request);

    Admin queryAdmin(Integer id);

    void updateAdmin(Admin admin);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct (String loginAcct);
}
