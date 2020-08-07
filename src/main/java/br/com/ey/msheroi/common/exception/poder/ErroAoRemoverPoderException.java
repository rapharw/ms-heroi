package br.com.ey.msheroi.common.exception.poder;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoRemoverPoderException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível remover o poder";

    public ErroAoRemoverPoderException(){
        super(MESSAGE);
    }
}
