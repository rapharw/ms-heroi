package br.com.ey.msheroi.common.exception.poder;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class ErroAoCadastrarUmPoderException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível cadastrar o poder";

    public ErroAoCadastrarUmPoderException(){
        super(MESSAGE);
    }
}
