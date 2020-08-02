package br.com.ey.msheroi.exception.poder;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoAlterarUmPoderException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível alterar o heroi";

    public ErroAoAlterarUmPoderException(){
        super(MESSAGE);
    }
}
