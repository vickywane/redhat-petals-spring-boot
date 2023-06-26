package redhat.engineering.ebikes.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import redhat.engineering.ebikes.entities.Service_User;

import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails {
    private String name;
    private String password;
    private String authorities;

    public UserInfoUserDetails(Service_User user) {
        name=user.getFullname();
        password=user.getPassword();
        authorities=user.getUser_role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
