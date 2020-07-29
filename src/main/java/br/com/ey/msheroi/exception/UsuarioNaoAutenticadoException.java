package br.com.ey.msheroi.exception;

public class UsuarioNaoAutenticadoException extends DefaultErrorException {

    private static final String MESSAGE = "Usuário não autorizado";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
