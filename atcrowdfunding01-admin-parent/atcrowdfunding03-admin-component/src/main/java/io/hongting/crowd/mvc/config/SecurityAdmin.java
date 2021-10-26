package io.hongting.crowd.mvc.config;

import io.hongting.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

/**
 * @author hongting
 * @create 2021 10 06 7:56 PM
 */
public class SecurityAdmin extends User {

    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getLogin(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
        this.originalAdmin.setUserPswd(null);
    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }

    public void setOriginalAdmin(Admin originalAdmin) {
        this.originalAdmin = originalAdmin;
    }
}
