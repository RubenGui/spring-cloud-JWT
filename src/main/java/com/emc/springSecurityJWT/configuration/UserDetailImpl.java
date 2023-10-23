package com.emc.springSecurityJWT.configuration;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emc.springSecurityJWT.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserDetailImpl implements UserDetails {
    private String login;
    private Long id;
    private String name;
    private int territorialServiceId;
    private int type;
    private String alterBy;
    private LocalDate create;
    private String mail;
    private String key;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailImpl build(UserEntity user){
        List<GrantedAuthority> authorities =
                user.getAuthorities().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
        return new UserDetailImpl(user.getUsuLogin(),
                user.getId(),
                user.getUsuNombre(),
                user.getUsuServicioTerritorial(),
                user.getUsuTipo(),
                user.getUsuUsuario(),
                user.getUsuFecha(),
                user.getUsuMail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.key;
    }

    @Override
    public String getUsername() {
        return this.login;
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
