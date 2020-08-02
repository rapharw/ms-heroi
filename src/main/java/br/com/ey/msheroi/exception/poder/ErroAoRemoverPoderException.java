package br.com.ey.msheroi.exception.poder;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoRemoverPoderException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível remover o poder";

    public ErroAoRemoverPoderException(){
        super(MESSAGE);
    }
}
