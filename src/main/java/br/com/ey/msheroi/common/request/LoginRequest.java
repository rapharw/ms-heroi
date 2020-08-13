package br.com.ey.msheroi.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.ey.msheroi.common.vo.Login;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {

    private String username;
    private String password;

    public Login getLoginInstance(){
        return Login.builder()
                .username(username)
                .password(password)
                .build();
    }
}
