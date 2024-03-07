package model.dao;

import exception.DaoException;
import exception.MyException;
import model.entite.Client;
import utilitaires.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;


/**
 * Classe pour l'accès aux données des clients dans la base de données.
 */
public class DaoClient {

    /**
     * Méthode pour trouver tous les clients dans la base de données.
     *
     * @return une liste de tous les clients trouvés.
     * @throws SQLException   si une erreur SQL survient lors de l'accès à la base de données.
     * @throws IOException    s'il y a une erreur d'entrée/sortie lors de la lecture ou de l'écriture de données.
     * @throws MyException    si une erreur spécifique à l'application se produit.
     * @throws DaoException   si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static ArrayList<Client> findAll() throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();
        Statement preparedStatement = null;

        // Requête SQL pour sélectionner tous les clients
        String query = "SELECT ID_CLIENT, NBR_EMPLOYE, RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, " +
                "NUM_TELEPHONE, E_MAIL, CHFFREAFAIR, COMMENTAIRE FROM client ORDER BY RAISON_SOCIALE ASC";

        // Vérifier si la connexion est établie
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème rencontré lors de la tentative de trouver un client dans la base de données.");
            throw new DaoException("Problème rencontré lors de la tentative de trouver un client dans la base de données.");
        } else {
            preparedStatement = connection.createStatement();

            ResultSet rs = preparedStatement.executeQuery(query);
            ArrayList<Client> clients = new ArrayList<>();
            while (rs.next()) {
                // Récupérer les données du client
                int idClient = rs.getInt("ID_CLIENT");
                int nbrEmploye = rs.getInt("NBR_EMPLOYE");
                String raisonSociale = rs.getString("RAISON_SOCIALE");
                String nomRue = rs.getString("NOM_RUE");
                String numRue = rs.getString("NUM_RUE");
                String codePostale = rs.getString("CODE_POSTALE");
                String ville = rs.getString("VILLE");
                String numTelephone = rs.getString("NUM_TELEPHONE");
                String eMail = rs.getString("E_MAIL");
                double chffreAffair = rs.getDouble("CHFFREAFAIR");
                String commentaire = rs.getString("COMMENTAIRE");

                // Créer un objet Client et l'ajouter à la liste
                Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, chffreAffair, nbrEmploye);
                clients.add(client);
            }
            rs.close();
            preparedStatement.close();

            return clients;
        }
    }

    /**
     * Méthode pour trouver un client par son nom dans la base de données.
     *
     * @param name le nom du client à rechercher.
     * @return le client trouvé ou null s'il n'est pas trouvé.
     * @throws SQLException   si une erreur SQL survient lors de l'accès à la base de données.
     * @throws IOException    s'il y a une erreur d'entrée/sortie lors de la lecture ou de l'écriture de données.
     * @throws MyException    si une erreur spécifique à l'application se produit.
     * @throws DaoException   si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static Client findByName(String name) throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();
        PreparedStatement preparedStatement = null;

        // Requête SQL pour sélectionner un client par son nom
        String query = "SELECT ID_CLIENT, NBR_EMPLOYE, RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, " +
                "NUM_TELEPHONE, E_MAIL, CHFFREAFAIR, COMMENTAIRE FROM client WHERE RAISON_SOCIALE = ?";

        // Vérifier si la connexion est établie
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème rencontré lors de la tentative de trouver un client dans la base de données.");
            throw new DaoException("Problème rencontré lors de la tentative de trouver un client dans la base de données.");
        } else {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name); // Assigner la valeur du paramètre

            ResultSet rs = preparedStatement.executeQuery();

            Client client = null;
            if (rs.next()) {
                // Récupérer les données du client
                int idClient = rs.getInt("ID_CLIENT");
                int nbrEmploye = rs.getInt("NBR_EMPLOYE");
                String raisonSociale = rs.getString("RAISON_SOCIALE");
                String nomRue = rs.getString("NOM_RUE");
                String numRue = rs.getString("NUM_RUE");
                String codePostale = rs.getString("CODE_POSTALE");
                String ville = rs.getString("VILLE");
                String numTelephone = rs.getString("NUM_TELEPHONE");
                String eMail = rs.getString("E_MAIL");
                double chffreAffair = rs.getDouble("CHFFREAFAIR");
                String commentaire = rs.getString("COMMENTAIRE");

                // Créer un objet Client
                client = new Client(idClient, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, chffreAffair, nbrEmploye);
            }

            // Fermeture des ressources
            rs.close();
            preparedStatement.close();

            return client;
        }
    }


    /**
     * Méthode pour créer un nouveau client dans la base de données.
     *
     * @param client le client à créer.
     * @throws SQLException   si une erreur SQL survient lors de l'accès à la base de données.
     * @throws IOException    s'il y a une erreur d'entrée/sortie lors de la lecture ou de l'écriture de données.
     * @throws MyException    si une erreur spécifique à l'application se produit.
     * @throws DaoException   si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void create(Client client) throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour insérer un nouveau client
        String query = "INSERT INTO client (RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, NUM_TELEPHONE, " +
                "E_MAIL, CHFFREAFAIR, COMMENTAIRE, NBR_EMPLOYE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Vérifier si la connexion est établie
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème rencontré lors de la tentative de créer un client dans la base de données.");
            throw new DaoException("Problème rencontré lors de la tentative de créer un client dans la base de données.");
        } else {
            // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution des valeurs aux paramètres de la déclaration
                preparedStatement.setString(1, client.getRaisonSociale());
                preparedStatement.setString(2, client.getNomDeRue());
                preparedStatement.setString(3, client.getNumeroDeRue());
                preparedStatement.setString(4, client.getCodePostal());
                preparedStatement.setString(5, client.getVille());
                preparedStatement.setString(6, client.getTelephone());
                preparedStatement.setString(7, client.getemail());
                preparedStatement.setDouble(8, client.getChiffreDaffaire());
                preparedStatement.setString(9, client.getCommentaire());
                preparedStatement.setInt(10, client.getNombreEmployer());

                // Exécution de la déclaration pour insérer le client dans la base de données
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * Méthode pour supprimer un client de la base de données.
     *
     * @param idClient l'identifiant du client à supprimer.
     * @throws SQLException   si une erreur SQL survient lors de l'accès à la base de données.
     * @throws IOException    s'il y a une erreur d'entrée/sortie lors de la lecture ou de l'écriture de données.
     * @throws MyException    si une erreur spécifique à l'application se produit.
     * @throws DaoException   si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void delete(int idClient) throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour supprimer un client en fonction de son ID
        String query = "DELETE FROM client WHERE ID_CLIENT = ?";

        // Vérifier si la connexion est établie
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème rencontré lors de la tentative de supprimer un client dans la base de données.");
            throw new DaoException("Problème rencontré lors de la tentative de supprimer un client dans la base de données.");
        } else {
            // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution de la valeur au paramètre de la déclaration
                preparedStatement.setInt(1, idClient);

                // Exécution de la déclaration pour supprimer le client de la base de données
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * Méthode pour mettre à jour les informations d'un client dans la base de données.
     *
     * @param client le client avec les informations mises à jour.
     * @throws SQLException   si une erreur SQL survient lors de l'accès à la base de données.
     * @throws IOException    s'il y a une erreur d'entrée/sortie lors de la lecture ou de l'écriture de données.
     * @throws MyException    si une erreur spécifique à l'application se produit.
     * @throws DaoException   si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void update(Client client) throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour mettre à jour un client en fonction de son ID
        String query = "UPDATE client SET RAISON_SOCIALE = ?, NOM_RUE = ?, NUM_RUE = ?, CODE_POSTALE = ?, " +
                "VILLE = ?, NUM_TELEPHONE = ?, E_MAIL = ?, CHFFREAFAIR = ?, COMMENTAIRE = ?, NBR_EMPLOYE = ? WHERE ID_CLIENT = ?";


        // Vérifier si la connexion est établie
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème rencontré lors de la tentative de mise à jour du client dans la base de données.");
            throw new DaoException("Problème rencontré lors de la tentative de mise à jour du client dans la base de données.");
        } else {
            // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution des valeurs aux paramètres de la déclaration
                preparedStatement.setString(1, client.getRaisonSociale());
                preparedStatement.setString(2, client.getNomDeRue());
                preparedStatement.setString(3, client.getNumeroDeRue());
                preparedStatement.setString(4, client.getCodePostal());
                preparedStatement.setString(5, client.getVille());
                preparedStatement.setString(6, client.getTelephone());
                preparedStatement.setString(7, client.getemail());
                preparedStatement.setDouble(8, client.getChiffreDaffaire());
                preparedStatement.setString(9, client.getCommentaire());
                preparedStatement.setInt(10, client.getNombreEmployer());
                preparedStatement.setInt(11, client.getId());

                // Exécution de la déclaration pour mettre à jour le client dans la base de données
                preparedStatement.executeUpdate();
            }
        }
    }
}


