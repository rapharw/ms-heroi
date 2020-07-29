package br.com.ey.msheroi.vo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static br.com.ey.msheroi.config.security.utils.EncryptDecryptUtils.decrypt;

@Builder
@Data
@Entity
@Table(name = "LOGIN")
@NoArgsConstructor
@AllArgsConstructor
public class Login implements Serializable {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public boolean checkPassword(String password){
        return this.password != null && decrypt(this.password).equals(password);
    }
}
