package br.com.ey.msheroi.exception;

public class ErroAoCadastrarUmHeroiException extends DefaultErrorException{

    private static final String MESSAGE = "Não foi possível cadastrar o heroi";

    private Exception exception;

    public ErroAoCadastrarUmHeroiException(){
        super(MESSAGE);
    }
}
