package br.com.ey.msheroi.exception;

public class ErroAoBuscarHeroisException extends DefaultErrorException {

    private static final String MESSAGE = "Não foi possível buscar os herois";

    public ErroAoBuscarHeroisException(){
        super(MESSAGE);
    }
}
