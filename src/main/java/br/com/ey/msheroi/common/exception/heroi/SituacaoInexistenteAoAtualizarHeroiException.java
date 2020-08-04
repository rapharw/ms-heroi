package br.com.ey.msheroi.common.exception.heroi;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class SituacaoInexistenteAoAtualizarHeroiException extends DefaultErrorException {

    private static final String MESSAGE = "Nao foi possível atualizar a situacao do heroi. A situacao informada e invalido";

    public SituacaoInexistenteAoAtualizarHeroiException(){
        super(MESSAGE);
    }
}
