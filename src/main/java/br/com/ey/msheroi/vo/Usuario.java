package br.com.ey.msheroi.vo;

import br.com.ey.msheroi.config.security.enums.PerfilUsuarioEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidade Usuario
 */
@Builder
@Entity
@Table(name = "USUARIOS")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    @Getter
    private Long id;

    @Column(name = "nome", nullable = false)
    @Getter
    private String nome;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    private PerfilUsuarioEnum perfilUsuario;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Login login;

    @Transient
    public String getUsername(){
        if(login != null)
            return this.login.getUsername();
        return null;
    }

    @Transient
    public boolean isAdmin(){
        return PerfilUsuarioEnum.ROLE_ADM == perfilUsuario;
    }
}
