package controleur;

import exception.ControleurExcpetion;
import exception.DaoException;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Acceuil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Le contrôleur principal de l'interface d'accueil de l'application.
 */
public class ControleurAccueil {
    /**
     * Initialise l'interface d'accueil.
     */
    public static void init() {
      Acceuil acceuil = new Acceuil();
      acceuil.setVisible(true);
    }
    /**
     * Démarre le formulaire spécifié.
     *
     * @param string le type de formulaire à démarrer.

     */
    public static void startFormulaire(String string)  {
        ControleurFormulaire.init(string);
    }

    /**
     * Démarre l'affichage pour le type spécifié.
     *
     * @param string le type d'affichage à démarrer.
     */
    public static void startAffichage(String string){
        ControleurAffichage.init(string);
    }

    /**
     * Sélectionne un client à partir de la liste des clients disponibles.
     *
     * @throws MyException      si une exception générale se produit.
     * @throws SQLException    si une erreur SQL se produit lors de l'accès à la base de données.
     * @throws IOException     si une erreur d'entrée/sortie se produit.
     * @throws ControleurExcpetion si une exception spécifique au contrôleur se produit.
     * @throws DaoException     si une erreur DAO se produit.
     */
    public static void selectClient(String selectedClientName) throws Exception {
        ArrayList<Client> clients;
        try {
            clients = DaoClient.findAll();
        } catch (DaoException e) {
            throw new DaoException("selection de client echoué ");
        }

        // Vérifier si la liste des prospects est vided
        if (clients.isEmpty()) {
            throw new ControleurExcpetion("Aucun prospect trouvé.");

        }

        // Créer un tableau de noms de prospects pour la boîte de dialogue
        if (selectedClientName != null) {
            // Rechercher le prospect sélectionné dans la liste des prospects
            Client selectedClient = null;
            for (Client client : clients) {
                if (client.getRaisonSociale().equals(selectedClientName)) {
                    selectedClient = client;
                    break;
                }
            }

            // Vérifier si le prospect sélectionné a été trouvé
            if (selectedClient!= null) {
                // Mettre à jour le formulaire avec les informations du prospect sélectionné
                ControleurFormulaire.clientVise = selectedClient; // Assigner le prospect sélectionné
            } else {
                throw new ControleurExcpetion("Le Client sélectionné n'a pas été trouvé.");
            }
        }

    }

    /**
     * Sélectionne un prospect à partir de la liste des prospects disponibles.
     *
     * @throws MyException si une exception générale se produit.
     */
    public static void selectProspect(String selectedProspectName) throws Exception {
        ArrayList<Prospect> prospects;
        try {
            prospects = DaoProspect.findAll();
        } catch (DaoException e) {
            throw new DaoException("selection de prospoect echoué ");
        }

        // Vérifier si la liste des prospects est vide
        if (prospects.isEmpty()) {
            throw new ControleurExcpetion("Aucun prospect trouvé.");

        }

        // Créer un tableau de noms de prospects pour la boîte de dialogue
       if (selectedProspectName != null) {
            // Rechercher le prospect sélectionné dans la liste des prospects
            Prospect selectedProspect = null;
            for (Prospect prospect : prospects) {
                if (prospect.getRaisonSociale().equals(selectedProspectName)) {
                    selectedProspect = prospect;
                    break;
                }
            }

            // Vérifier si le prospect sélectionné a été trouvé
            if (selectedProspect != null) {
                // Mettre à jour le formulaire avec les informations du prospect sélectionné
                ControleurFormulaire.prospectVise = selectedProspect; // Assigner le prospect sélectionné
            } else {
                throw new ControleurExcpetion("Le Prospect sélectionné n'a pas été trouvé.");
            }
        }

    }
    public static ArrayList <Prospect> findAllProspect() throws Exception{

       return DaoProspect.findAll();
    }
    public static ArrayList <Client> findAllClient() throws Exception{

        return DaoClient.findAll();
    }
}
