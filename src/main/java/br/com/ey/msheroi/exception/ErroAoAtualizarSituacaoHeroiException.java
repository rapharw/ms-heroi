package br.com.ey.msheroi.exception;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;

public class ErroAoAtualizarSituacaoHeroiException extends DefaultErrorException{

    private static final String MESSAGE = "Não foi possível atualizar o heroi para a situacao %s";

    private Exception exception;

    public ErroAoAtualizarSituacaoHeroiException(TipoSituacaoEnum tipoSituacaoEnum){
        super(String.format(MESSAGE, tipoSituacaoEnum.name()));
    }
}
