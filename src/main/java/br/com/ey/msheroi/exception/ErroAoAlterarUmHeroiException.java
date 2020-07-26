package br.com.ey.msheroi.exception;

public class ErroAoAlterarUmHeroiException extends DefaultErrorException{

    private static final String MESSAGE = "Não foi possível alterar o heroi";

    private Exception exception;

    public ErroAoAlterarUmHeroiException(){
        super(MESSAGE);
    }
}
