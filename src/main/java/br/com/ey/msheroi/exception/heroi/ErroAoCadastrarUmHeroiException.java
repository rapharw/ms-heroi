package br.com.ey.msheroi.exception.heroi;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoCadastrarUmHeroiException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível cadastrar o heroi";

    private Exception exception;

    public ErroAoCadastrarUmHeroiException(){
        super(MESSAGE);
    }
}