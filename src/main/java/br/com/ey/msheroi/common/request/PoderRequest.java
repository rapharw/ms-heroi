package br.com.ey.msheroi.common.request;

import br.com.ey.msheroi.common.vo.Poder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoderRequest implements Serializable {

    private Integer id;
    private String descricao;

    public Poder getPoderInstance(){
        return Poder.builder()
                .id(id)
                .descricao(descricao)
                .build();
    }
}
