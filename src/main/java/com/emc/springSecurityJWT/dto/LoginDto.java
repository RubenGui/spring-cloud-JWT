package com.emc.springSecurityJWT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@ToString
public class LoginDto {
    @NotBlank
    private String login;
    @NotBlank
    private String key;
}
