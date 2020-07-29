package br.com.ey.msheroi.config.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de usuario logado controlada pelo Spring
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal {

    private Integer id;
    private String username;
    private boolean isAdmin;
}
