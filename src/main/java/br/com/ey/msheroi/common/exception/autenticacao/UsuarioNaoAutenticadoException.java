package br.com.ey.msheroi.common.exception.autenticacao;

import br.com.ey.msheroi.common.exception.DefaultErrorException;

public class UsuarioNaoAutenticadoException extends DefaultErrorException {

    private static final String MESSAGE = "Usuário não autorizado";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
