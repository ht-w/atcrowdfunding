package io.hongting.crowd.mvc.config;

import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.Role;
import io.hongting.crowd.service.api.AdminService;
import io.hongting.crowd.service.api.AuthService;
import io.hongting.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author hongting
 * @create 2021 10 06 8:03 PM
 */

@Component
public class CrowdUserDetailsService implements UserDetailsService {



    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Admin admin = adminService.getAdminByLoginAcct(s);
        List<Role> assignedRole = roleService.getAssignedRole(admin.getId());
        List<String> authNames = authService.getAuthNameByAdminId(admin.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : assignedRole) {
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            grantedAuthorities.add(simpleGrantedAuthority);
        }

        for (String authName : authNames) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            grantedAuthorities.add(simpleGrantedAuthority);
        }

        return new SecurityAdmin(admin, grantedAuthorities);

    }
}
