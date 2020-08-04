package br.com.ey.msheroi.common.exception.poder;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoBuscarPoderesException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os poderes";

    public ErroAoBuscarPoderesException(){
        super(MESSAGE);
    }
}
