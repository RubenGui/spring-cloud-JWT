package com.emc.springSecurityJWT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String login;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String login, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.login = login;
        this.authorities = authorities;
    }
}

