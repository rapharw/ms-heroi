package br.com.ey.msheroi.common.exception.universo;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoAlterarUmUniversoException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível alterar o universo";

    public ErroAoAlterarUmUniversoException(){
        super(MESSAGE);
    }
}
