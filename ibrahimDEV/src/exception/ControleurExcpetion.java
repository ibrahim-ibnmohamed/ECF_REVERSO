package exception;

/**
 * Une exception spécifique au contrôleur de l'application.
 */
public class ControleurExcpetion extends Exception {

    /**
     * Constructeur avec un message d'erreur spécifique.
     *
     * @param message le message d'erreur spécifique.
     */
    public ControleurExcpetion(String message) {
        super(message);
    }
}
