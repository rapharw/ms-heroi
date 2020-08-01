package br.com.ey.msheroi.exception.heroi;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class HeroiNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Heroi nao encontrado";

    public HeroiNaoEncontradoException(){
        super(MESSAGE);
    }
}
