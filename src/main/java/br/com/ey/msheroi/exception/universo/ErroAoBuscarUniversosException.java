package br.com.ey.msheroi.exception.universo;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoBuscarUniversosException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os universos";

    public ErroAoBuscarUniversosException(){
        super(MESSAGE);
    }
}
