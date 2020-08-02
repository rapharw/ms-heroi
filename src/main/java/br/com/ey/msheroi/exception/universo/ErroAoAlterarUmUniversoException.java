package br.com.ey.msheroi.exception.universo;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoAlterarUmUniversoException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível alterar o universo";

    public ErroAoAlterarUmUniversoException(){
        super(MESSAGE);
    }
}
