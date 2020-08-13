package br.com.ey.msheroi.common.request;

import br.com.ey.msheroi.common.vo.Universo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversoRequest implements Serializable {

    private Integer id;
    private String descricao;

    public Universo getUniversoInstance(){
        return Universo.builder()
                .id(id)
                .descricao(descricao)
                .build();
    }
}
