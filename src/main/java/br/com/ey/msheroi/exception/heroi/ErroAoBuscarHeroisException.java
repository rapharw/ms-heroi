package br.com.ey.msheroi.exception.heroi;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoBuscarHeroisException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os herois";

    public ErroAoBuscarHeroisException(){
        super(MESSAGE);
    }
}
