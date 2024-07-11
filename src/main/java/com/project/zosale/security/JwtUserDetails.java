package com.project.zosale.security;

import com.project.zosale.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data

public class JwtUserDetails implements UserDetails {

    private long id ;
    private String Username;
    private String password;
    private Collection<? extends  GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.Username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static JwtUserDetails create(User user){
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(),user.getEmail(), user.getPassword(), authoritiesList);

    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
