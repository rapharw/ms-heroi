package br.com.ey.msheroi.common.exception.heroi;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoAlterarUmHeroiException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível alterar o heroi";

    private Exception exception;

    public ErroAoAlterarUmHeroiException(){
        super(MESSAGE);
    }
}
