package exception;

/**
 * Une exception personnalisée pour l'application.
 */
public class MyException extends Exception {

    /**
     * Constructeur avec un message d'erreur spécifique.
     *
     * @param message le message d'erreur spécifique.
     */
    public MyException(String message) {
        super(message);
    }
}
