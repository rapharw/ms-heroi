package br.com.ey.msheroi.exception.autenticacao;

import br.com.ey.msheroi.exception.DefaultErrorException;

public class UsuarioNaoAutenticadoException extends DefaultErrorException {

    private static final String MESSAGE = "Usuário não autorizado";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
