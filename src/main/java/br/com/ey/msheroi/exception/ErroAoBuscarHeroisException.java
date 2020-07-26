package br.com.ey.msheroi.exception;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;

public class ErroAoBuscarHeroisException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os herois pela situacao %s";

    private Exception exception;

    public ErroAoBuscarHeroisException(TipoSituacaoEnum tipoSituacaoEnum){
        super(String.format(MESSAGE, tipoSituacaoEnum.name()));
    }
}
