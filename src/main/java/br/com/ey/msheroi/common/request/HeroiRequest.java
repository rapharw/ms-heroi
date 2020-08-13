package br.com.ey.msheroi.common.request;

import br.com.ey.msheroi.common.enums.Situacao;
import br.com.ey.msheroi.common.vo.Heroi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroiRequest implements Serializable {

    private Long id;
    private String nome;
    private UniversoRequest universo;
    private Situacao situacao;
    private Set<PoderRequest> poderes;

    public Heroi getHeroiInstance(){


        Heroi heroiBuild = Heroi.builder()
                .nome(nome)
                .situacao(situacao)
                .universo(universo.getUniversoInstance())
                .build();

        poderes.forEach(poderRequest -> heroiBuild.adicionaUmPoder(poderRequest.getPoderInstance()));

        return heroiBuild;
    }
}
