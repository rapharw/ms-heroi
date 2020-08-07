package br.com.ey.msheroi.common.exception.universo;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoBuscarUniversosException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os universos";

    public ErroAoBuscarUniversosException(){
        super(MESSAGE);
    }
}
