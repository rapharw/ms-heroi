package br.com.ey.msheroi.enums;

public enum Situacao {
    ATIVO,
    INATIVO;

    public static boolean isSituacaoValida(Situacao situacao){
        return Situacao.ATIVO == situacao || Situacao.INATIVO == situacao;
    }
}
