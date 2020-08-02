package br.com.ey.msheroi.exception.poder;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class ErroAoCadastrarUmPoderException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível cadastrar o poder";

    public ErroAoCadastrarUmPoderException(){
        super(MESSAGE);
    }
}
