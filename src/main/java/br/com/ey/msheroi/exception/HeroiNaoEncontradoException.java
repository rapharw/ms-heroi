package br.com.ey.msheroi.exception;

public class HeroiNaoEncontradoException extends DefaultErrorException{

    private static final String MESSAGE = "Heroi nao encontrado";

    public HeroiNaoEncontradoException(){
        super(MESSAGE);
    }
}
