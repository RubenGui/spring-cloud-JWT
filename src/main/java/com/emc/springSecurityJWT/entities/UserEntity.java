package com.emc.springSecurityJWT.entities;

import com.emc.springSecurityJWT.enums.E_UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FOR_000_USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @Column(name = "USU_USUARIOID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "USU_LOGIN", unique = true)
    private String usuLogin;

    @Column(name = "USU_KEY")
    private String password;

    @Column(name = "USU_NOMBRE")
    private String usuNombre;

    @Column(name = "USU_SERVICIOTERRITORIALID")
    private Integer usuServicioTerritorial;

    @Column(name = "USU_TIPO")
    private Integer usuTipo;

    @Column(name = "USU_FECHA")
    private LocalDate usuFecha;

    @Column(name = "USU_USUARIO")
    private String usuUsuario;

    @Column(name = "USU_MAIL")
    private String usuMail;

    @Transient
    private List<E_UserAuthority> roles = new ArrayList<>();

    public UserEntity(String login, String name, String email, String key) {
        this.usuLogin=login;
        this.password=key;
        this.usuNombre=name;
        this.usuServicioTerritorial =0;
        this.usuTipo = 0;
        this.usuFecha=LocalDate.now();
        this.usuUsuario="Usuario creado por: XXX";
        this.usuMail=email;
    }
    public List<E_UserAuthority> getRoles() {
        return getAuthorities();
    }
    public List<E_UserAuthority> getAuthorities() {
        List<E_UserAuthority> authorities = new ArrayList<>();
        if (this.usuTipo == 0) {
            authorities.add(E_UserAuthority.ADMINISTRADOR);
            authorities.add(E_UserAuthority.GESTOR);
            authorities.add(E_UserAuthority.CONSULTOR);
            this.roles = authorities;
        }
        if (this.usuTipo == 1) {
            authorities.add(E_UserAuthority.GESTOR);
            authorities.add(E_UserAuthority.CONSULTOR);
            this.roles = authorities;
        }
        if (this.usuTipo == 2) {
            authorities.add(E_UserAuthority.CONSULTOR);
            this.roles = authorities;
        }
        return authorities;
    }
}
