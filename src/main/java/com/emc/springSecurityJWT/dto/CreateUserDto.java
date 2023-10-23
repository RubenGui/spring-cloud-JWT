package com.emc.springSecurityJWT.dto;

import com.emc.springSecurityJWT.enums.E_UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserDto {
    @NotBlank
    private String login;
    @NotBlank
    private String name;
    @NotBlank
    private Integer terrServiceId;
    @NotBlank
    private E_UserAuthority type;
    @NotBlank
    private String createdBy;
    @NotBlank
    private LocalDate alterDate;
    @NotBlank
    private String key;
    @Email
    private String email;

    public CreateUserDto(String login, String name, Integer terrServiceId, E_UserAuthority type, String key, String email) {
        this.login = login;
        this.name = name;
        this.terrServiceId = terrServiceId;
        this.type = type;
        this.createdBy = "createdBy";
        this.alterDate = LocalDate.now();
        this.key = "tragsa";
        this.email = email;
    }
}
