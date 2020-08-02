package br.com.ey.msheroi.exception.poder;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class PoderNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Poder nao encontrado";

    public PoderNaoEncontradoException(){
        super(MESSAGE);
    }
}
