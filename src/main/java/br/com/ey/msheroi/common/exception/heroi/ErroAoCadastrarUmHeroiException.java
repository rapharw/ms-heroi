package br.com.ey.msheroi.common.exception.heroi;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoCadastrarUmHeroiException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível cadastrar o heroi";

    public ErroAoCadastrarUmHeroiException(){
        super(MESSAGE);
    }
}
