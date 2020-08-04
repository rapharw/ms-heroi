package br.com.ey.msheroi.common.exception.universo;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class UniversoNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Universo nao encontrado";

    public UniversoNaoEncontradoException(){
        super(MESSAGE);
    }
}
