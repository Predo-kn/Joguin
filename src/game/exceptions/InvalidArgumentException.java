package game.exceptions;

/**
 * Exceção para argumentos inválidos
 */
public class InvalidArgumentException extends GameException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
