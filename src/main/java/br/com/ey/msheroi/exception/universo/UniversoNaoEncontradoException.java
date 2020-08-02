package br.com.ey.msheroi.exception.universo;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class UniversoNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Universo nao encontrado";

    public UniversoNaoEncontradoException(){
        super(MESSAGE);
    }
}
