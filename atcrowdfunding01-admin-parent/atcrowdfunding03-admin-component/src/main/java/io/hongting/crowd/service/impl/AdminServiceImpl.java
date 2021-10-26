package io.hongting.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.AdminExample;
import io.hongting.crowd.exception.LoginAcctEditException;
import io.hongting.crowd.exception.LoginAcctExistedException;
import io.hongting.crowd.exception.LoginFailedException;
import io.hongting.crowd.mapper.AdminMapper;
import io.hongting.crowd.service.api.AdminService;
import io.hongting.crowd.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author hongting
 * @create 2021 09 26 4:09 PM
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public  void saveAdmin(Admin admin) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        admin.setCreateTime(createTime);

        String encryptedpswd = passwordEncoder.encode(admin.getUserPswd());
        admin.setUserPswd(encryptedpswd);

        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctExistedException(ResultConstant.MESSAGE_ACCT_EXISTED);
            }
        }
    }

    @Override
    public List<Admin> getAll()  {
        return adminMapper.selectByExample(new AdminExample());
    }


    /**
     * Retrieve admin by its login account and password
     * @param loginAcct
     * @param userPwsd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPwsd) {

        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins==null || admins.isEmpty()){
            throw new LoginFailedException(ResultConstant.MESSAGE_LOGIN_FAILED);
        }
        if(admins.size()>1){
            throw new RuntimeException(ResultConstant.MESSAGE_LOGIN_NOTUNIQUE);
        }
        Admin admin = admins.get(0);
        String encryptedPwsd = Util.MD5encrypt(userPwsd);
        if (!admin.getUserPswd().equals(encryptedPwsd)){
            throw new LoginFailedException(ResultConstant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
     }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);
        return new PageInfo<>(admins);
    }

    @Override
    public void removeAdmin(Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin admin =(Admin) session.getAttribute(ResultConstant.ATTR_NAME_LOGIN_ADMIN);
        if(admin.getId().equals(id)){
            throw new RuntimeException(ResultConstant.MESSAGE_REMOVE_CURRENTADMIN);
        }
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin queryAdmin(Integer id) {
       return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdmin(Admin admin){
        try{
            adminMapper.updateByPrimaryKeySelective(admin);
        }catch(Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctEditException(ResultConstant.MESSAGE_ACCT_EDITERROR);
            }
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        adminMapper.clearRelationship(adminId);

        if(roleIdList!=null && !roleIdList.isEmpty()) {
            adminMapper.assignRoles(adminId, roleIdList);
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginEqualTo(loginAcct);
        return adminMapper.selectByExample(adminExample).get(0);
    }



}

