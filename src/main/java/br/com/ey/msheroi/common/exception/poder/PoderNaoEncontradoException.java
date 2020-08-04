package br.com.ey.msheroi.common.exception.poder;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class PoderNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Poder nao encontrado";

    public PoderNaoEncontradoException(){
        super(MESSAGE);
    }
}
