package com.aditya7812.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad que representa a un usuario en el sistema.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String lastName = "unknown";

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    /**
     * Obtiene los roles asignados al usuario.
     *
     * @return Una lista de los roles asignados al usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return true si la cuenta no ha expirado, false si ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada o no.
     *
     * @return true si la cuenta no está bloqueada, false si está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     *
     * @return true si las credenciales no han expirado, false si han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado o no.
     *
     * @return true si el usuario está habilitado, false si no está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}