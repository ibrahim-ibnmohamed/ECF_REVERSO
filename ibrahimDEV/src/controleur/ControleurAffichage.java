package controleur;

import exception.DaoException;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Affichage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Le contrôleur pour l'affichage des données dans l'application.
 */
public class ControleurAffichage {

    /**
     * Initialise l'affichage avec le choix spécifié.
     *
     * @param choix le choix spécifié pour l'affichage.
     */
    public static void init(String choix) throws MyException, SQLException, IOException, DaoException {
        Affichage affichage = new Affichage(choix);
        affichage.setVisible(true);
    }

    /**
     * Récupère tous les clients de la base de données.
     *
     * @return une liste de tous les clients.
     */
    public static ArrayList<Client> findAllClient() throws MyException, SQLException, IOException, DaoException {
            return DaoClient.findAll();
    }

    /**
     * Récupère tous les prospects de la base de données.
     *
     * @return une liste de tous les Prospects.
     */
    public static ArrayList<Prospect> findAllProspect() throws MyException, SQLException, IOException, DaoException {
        return DaoProspect.findAll();
    }


    /**
     * Démarre l'interface d'accueil.
     */
    public static void startAccueil() {
        ControleurAccueil.init();
    }
}
