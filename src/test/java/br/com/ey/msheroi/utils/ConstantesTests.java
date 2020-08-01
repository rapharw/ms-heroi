package br.com.ey.msheroi.utils;

import br.com.ey.msheroi.enums.Situacao;
import br.com.ey.msheroi.enums.TipoUniversoEnum;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Poder;
import br.com.ey.msheroi.vo.Universo;

public class ConstantesTests {

    private ConstantesTests(){/*default*/}

    //PODER
    public static final Integer     ID_PODER_PALACIO_DA_MEMORIA = 1;
    public static final String      DESCRICAO_PODER_PALACIO_DA_MEMORIA_EXPECTED = "Palácio da Memória";
    public static final Integer     ID_PODER_SUPER_VELOCIDADE = 2;
    public static final String      DESCRICAO_PODER_SUPER_VELOCIDADE_EXPECTED = "Super velocidade";
    public static final Integer     ID_PODER_INEXISTENTE = 999;

    //UNIVERSO
    public static final Integer     ID_EY_COMICS = TipoUniversoEnum.EY_COMICS.getId();
    public static final String      DESCRICAO_EY_COMICS_EXPECTED = "EY Comics";
    public static final Integer     ID_UNIVERSO_INEXISTENTE = 999;

    //HEROI
    public static final Long        ID_HEROI_CRIADO = 1L;
    public static final String      NOME_HEROI = "Sherlock H.";
    public static final String      NOME_HEROI2_CACHED = "Arya Stark";
    public static final Integer     ID_HEROI_INEXISTENTE = 999;



    //USUARIO
    public static final Long        ID_USUARIO_RAPHAEL = 1L;
    public static final String      NOME_USUARIO_RAPHAEL = "Raphael";
    public static final String      LOGIN_USUARIO_RAPHAEL = "r0001";

    public static final Long        ID_USUARIO_RENATO = 2L;
    public static final String      NOME_USUARIO_RENATO = "r0002";

    public static final Long        ID_USUARIO_WANDERLEY = 3L;
    public static final String      NOME_USUARIO_WANDERLEY = "r0003";


    public static Heroi newHeroInstance(int i){

        //Cria uma instancia de um heroi
        Heroi heroi = Heroi.builder()
                .nome("Herói de num - " + i)
                .situacao(Situacao.ATIVO)
                .universo(Universo.builder().id(ID_EY_COMICS).build())
                .build();

        //Adiciona um poder p/ o Heroi
        heroi.adicionaUmPoder(Poder.builder()
                .id(ID_PODER_PALACIO_DA_MEMORIA)
                .build());
        return heroi;
    }
}
