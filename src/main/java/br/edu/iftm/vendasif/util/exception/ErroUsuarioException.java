package br.edu.iftm.vendasif.util.exception;

/**
 *
 * @author danilo
 */
public class ErroUsuarioException extends Exception{

    public ErroUsuarioException(String message) {
        super(message);
    }

    public ErroUsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
