package exception;

/**
 * Une exception spécifique à la couche d'accès aux données (DAO) de l'application.
 */
public class DaoException extends Exception {

    /**
     * Constructeur avec un message d'erreur spécifique.
     *
     * @param message le message d'erreur spécifique.
     */
    public DaoException(String message) {
        super(message);
    }
}
