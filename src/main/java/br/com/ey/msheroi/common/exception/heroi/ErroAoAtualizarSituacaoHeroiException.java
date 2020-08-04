package br.com.ey.msheroi.common.exception.heroi;

import br.com.ey.msheroi.common.enums.Situacao;
import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoAtualizarSituacaoHeroiException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível atualizar o heroi para a situacao %s";

    private Exception exception;

    public ErroAoAtualizarSituacaoHeroiException(Situacao situacao){
        super(String.format(MESSAGE, situacao.name()));
    }
}
