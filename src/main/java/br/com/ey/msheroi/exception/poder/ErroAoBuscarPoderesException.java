package br.com.ey.msheroi.exception.poder;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoBuscarPoderesException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os poderes";

    public ErroAoBuscarPoderesException(){
        super(MESSAGE);
    }
}
