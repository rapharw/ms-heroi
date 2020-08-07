package br.com.ey.msheroi.common.exception.heroi;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class HeroiNaoEncontradoException extends DefaultErrorException {

    private static final String MESSAGE = "Heroi nao encontrado";

    public HeroiNaoEncontradoException(){
        super(MESSAGE);
    }
}
