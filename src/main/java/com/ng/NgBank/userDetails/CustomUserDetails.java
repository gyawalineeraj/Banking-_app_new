package com.ng.NgBank.userDetails;

import com.ng.NgBank.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class CustomUserDetails implements UserDetails {

    private final User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map((r) -> new SimpleGrantedAuthority(r.getName())).collect(
                Collectors.toUnmodifiableSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName(){
        return user.getFirstName() + user.getLastName();
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public String getLastName(){
        return user.getLastName();
    }
}
