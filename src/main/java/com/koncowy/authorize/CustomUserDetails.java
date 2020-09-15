package com.koncowy.authorize;


import com.koncowy.model.Role;
import com.koncowy.model.UserApp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserApp implements UserDetails {


    public CustomUserDetails(UserApp userApp) {
        super(userApp);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : super.getRoles()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
            list.add(simpleGrantedAuthority);
        }
        return list;
//to jest to samo co wyzej, ale wyrazone w strumieniach
//        return super
//                .getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
//                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
