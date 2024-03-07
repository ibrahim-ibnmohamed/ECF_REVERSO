package application;

import controleur.ControleurAccueil;
import exception.DaoException;
import exception.MyException;
import utilitaires.FormatterLog;
import utilitaires.MyLogger;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * Classe principale de l'application.
 */
public class Main {

    /**
     * Constructeur de la classe principale.
     * @throws SQLException en cas d'erreur de base de données.
     * @throws IOException en cas d'erreur d'entrée/sortie.
     */
    public Main() throws SQLException, IOException {
    }

    /**
     * Méthode principale de l'application.
     * @param args arguments de la ligne de commande.
     * @throws MyException en cas d'exception générale.
     * @throws SQLException en cas d'erreur de base de données.
     * @throws IOException en cas d'erreur d'entrée/sortie.
     * @throws DaoException en cas d'exception DAO.
     */
    public static void main(String[] args) throws MyException, SQLException, IOException, DaoException {

        // Configuration du journal
        FileHandler fh = new FileHandler("logReverso.log", true);
        fh.setFormatter(new FormatterLog());
        MyLogger.LOGGER.setUseParentHandlers(false);
        MyLogger.LOGGER.addHandler(fh);

        // Début du programme
        MyLogger.LOGGER.log(Level.INFO, "Début du programme");

        // Initialisation du contrôleur d'accueil
        ControleurAccueil.init();

        // Fin du programme
        MyLogger.LOGGER.log(Level.INFO, "Fin du programme");
    }
}
