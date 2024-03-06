package model.dao;

import exception.DaoException;
import exception.MyException;
import model.entite.Client;
import utilitaires.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class DaoClient {
    public static ArrayList<Client>   findAll() throws SQLException, IOException, MyException, DaoException {

        Connection connection= new DaoConnection().getConnection();
        Statement preparedStatement = null;
        String query = "SELECT " +
                "ID_CLIENT, "+
                " NBR_EMPLOYE," +
                "RAISON_SOCIALE," +
                "NOM_RUE," +
                "NUM_RUE," +
                "CODE_POSTALE," +
                "VILLE," +
                "NUM_TELEPHONE," +
                "E_MAIL," +
                "CHFFREAFAIR," +
                "COMMENTAIRE" +
                " FROM client ORDER BY RAISON_SOCIALE ASC ";


        if(connection==null){
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de trouver un client dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de trouver un  client dans la base de données.");
        }else {
        preparedStatement =connection.createStatement();



        ResultSet rs =  preparedStatement.executeQuery(query);
        ArrayList<Client> clients = new ArrayList<>();
        while (rs.next()){

            int idClient =rs.getInt("ID_CLIENT");
           int nbrEmploye = rs.getInt("NBR_EMPLOYE");
           String raisonSociale = rs.getString("RAISON_SOCIALE");
           String nomRue = rs.getString("NOM_RUE");
           String numRue= rs.getString("NUM_RUE");
           String codePostale= rs.getString("CODE_POSTALE");
           String ville = rs.getString("VILLE");
           String numTelephone = rs.getString("NUM_TELEPHONE");
           String eMail = rs.getString("E_MAIL");
           double chffreAffair = rs.getDouble("CHFFREAFAIR");
           String commentaire = rs.getString("COMMENTAIRE");

            Client client= new Client(idClient,raisonSociale,numRue,nomRue,codePostale,numTelephone,ville,eMail,commentaire,chffreAffair,nbrEmploye);
            clients.add(client);
        }
        rs.close();
        preparedStatement.close();

        return clients;
    }}

    public static Client findByName(String name) throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        PreparedStatement preparedStatement = null;
        String query = "SELECT " +
                "ID_CLIENT, "+
                "NBR_EMPLOYE," +
                "RAISON_SOCIALE," +
                "NOM_RUE," +
                "NUM_RUE," +
                "CODE_POSTALE," +
                "VILLE," +
                "NUM_TELEPHONE," +
                "E_MAIL," +
                "CHFFREAFAIR," +
                "COMMENTAIRE" +
                " FROM client WHERE RAISON_SOCIALE = ?";
        if(connection==null){
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de trouver un client dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de trouver un  client dans la base de données.");
        }else {

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name); // Set the parameter value
        ResultSet rs = preparedStatement.executeQuery();

        Client client = null;
        if (rs.next()) {
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

            client = new Client(idClient, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, chffreAffair, nbrEmploye);


        }

        // Fermeture des ressources
        rs.close();
        preparedStatement.close();


        return client;
    }
    }


    public static void create(Client client) throws SQLException, IOException, MyException , DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour insérer un nouveau client
        String query = "INSERT INTO client (RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, NUM_TELEPHONE, E_MAIL, CHFFREAFAIR, COMMENTAIRE, NBR_EMPLOYE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if(connection==null){
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de crée un client dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de crée un  client dans la base de données.");
        }else {

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
            preparedStatement.executeUpdate();
        }

    }}
    public static void delete(int idClient) throws SQLException, IOException, MyException ,DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour supprimer un client en fonction de son ID
        String query = "DELETE FROM client WHERE ID_CLIENT = ?";
        if(connection==null){
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de supprimer un client dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de supprimer un  client dans la base de données.");
        }else{

        // Préparer la déclaration SQL avec des paramètres
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Attribution de la valeur à paramètre de la déclaration
            preparedStatement.setInt(1, idClient);

            // Exécution de la déclaration pour supprimer le client de la base de données
            preparedStatement.executeUpdate();
        }

    }}

    public static void update(Client client) throws SQLException, IOException, MyException,DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour mettre à jour un client en fonction de son ID
        String query = "UPDATE client SET RAISON_SOCIALE = ?, NOM_RUE = ?, NUM_RUE = ?, CODE_POSTALE = ?, VILLE = ?, " +
                "NUM_TELEPHONE = ?, E_MAIL = ?, CHFFREAFAIR = ?, COMMENTAIRE = ?, NBR_EMPLOYE = ? WHERE ID_CLIENT = ?";
        if(connection==null){
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de mise à jour du client dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de mise à jour du client dans la base de données.");
        }else{

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
    }}
}


