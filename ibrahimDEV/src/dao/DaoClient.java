package dao;

import exception.DaoException;
import exception.MyException;
import entite.Client;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe pour l'accès aux données des clients dans la base de données.
 */
public class DaoClient {

    /**
     * Méthode pour trouver tous les clients dans la base de données.
     *
     * @return une liste de tous les clients trouvés.
     * @throws DaoException si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static ArrayList<Client> findAll() throws Exception {
        Connection connection;
        Statement statement ;
        ResultSet resultSet ;
        ArrayList<Client> clients = new ArrayList<>();

        try {
            connection = DaoConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client");

            while (resultSet.next()) {
                Client client = extractClientFromResultSet(resultSet);
                clients.add(client);
            }
            return clients;

        } catch (SQLException e) {
            throw new SQLException("Problème rencontré lors de la recherche de tous les clients."+e.getMessage());
        }catch(IOException ioe){
           throw new DaoException("aucun client a été trouvé ") ;
        }


    }

    /**
     * Méthode pour trouver un client par son nom dans la base de données.
     *
     * @param name le nom du client à rechercher.
     * @return le client trouvé ou null s'il n'est pas trouvé.
     * @throws DaoException si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static Client findByName(String name) throws Exception {
        Connection connection ;
        PreparedStatement preparedStatement ;
        ResultSet resultSet;
        Client client = null;

        try {
            connection = DaoConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE RAISON_SOCIALE = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                client = extractClientFromResultSet(resultSet);
            }
            return client;

        } catch (SQLException | IOException e) {
            throw new DaoException("Problème rencontré lors de la recherche de tous les clients.");
        } catch (MyException e) {
            throw new MyException  ("Problème rencontré lors de la recherche du client par nom.");
        }


    }

    /**
     * Méthode pour créer un nouveau client dans la base de données.
     *
     * @param client le client à créer.
     * @throws DaoException si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void create(Client client) throws Exception {




        try (
           Connection connection = DaoConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (RAISON_SOCIALE, NOM_RUE," +
                    " NUM_RUE," +
                    " CODE_POSTALE, VILLE, NUM_TELEPHONE, E_MAIL, CHFFREAFAIR, COMMENTAIRE, NBR_EMPLOYE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){

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
        } catch (SQLException e) {
           if (e.getErrorCode() ==1062 ){
               throw new DaoException("La raison sociale '" + client.getRaisonSociale() + "' existe déjà dans la base de données.");
           }else if (e.getErrorCode()==1406){
               throw new DaoException("le numero de rue'" + client.getNumeroDeRue() + "est trop longe .");
           }else {
               throw new SQLException("Problème rencontré lors de la tentative de créer un client dans la base de données "+e.getMessage());
           }

            }
        }


    /**
     * Méthode pour supprimer un client de la base de données.
     *
     * @param idClient l'identifiant du client à supprimer.
     * @throws DaoException si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void delete(int idClient) throws Exception {
        Connection connection ;
        PreparedStatement preparedStatement;

        try {
            connection = DaoConnection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM client WHERE ID_CLIENT = ?");
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException("Problème rencontré lors de la tentative de supprimer un client dans la base de données.");
        }
    }

    /**
     * Méthode pour mettre à jour les informations d'un client dans la base de données.
     *
     * @param client le client avec les informations mises à jour.
     * @throws DaoException si une erreur spécifique à la couche d'accès aux données se produit.
     */
    public static void update(Client client) throws Exception {
        Connection connection ;
        PreparedStatement preparedStatement ;

        try {
            connection = DaoConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE client SET RAISON_SOCIALE = ?, NOM_RUE = ?," +
                    " NUM_RUE = ?, CODE_POSTALE = ?, VILLE = ?, NUM_TELEPHONE = ?, E_MAIL = ?, CHFFREAFAIR = ?, COMMENTAIRE = ?, NBR_EMPLOYE = ? WHERE ID_CLIENT = ?");

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

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() ==1062 ) {
                throw new DaoException("La raison sociale '" + client.getRaisonSociale() + "' existe déjà dans la base de données.");
            }else if (e.getErrorCode()==1406){
                    throw new DaoException("le numero de rue'" + client.getNumeroDeRue() + "est trop longe .");
            }else {
                throw new SQLException("erreur creat Client "+e.getMessage()+e.getErrorCode());
            }
        }
    }

    /**
     * Méthode utilitaire pour extraire un objet Client à partir d'un ResultSet.
     *
     * @param resultSet le ResultSet à partir duquel extraire les données du client.
     * @return un objet Client extrait du ResultSet.
     * @throws SQLException si une erreur SQL survient lors de la récupération des données du ResultSet.
     */
    private static Client extractClientFromResultSet(ResultSet resultSet) throws SQLException{
       try { int idClient = resultSet.getInt("ID_CLIENT");
        int nbrEmploye = resultSet.getInt("NBR_EMPLOYE");
        String raisonSociale = resultSet.getString("RAISON_SOCIALE");
        String nomRue = resultSet.getString("NOM_RUE");
        String numRue = resultSet.getString("NUM_RUE");
        String codePostale = resultSet.getString("CODE_POSTALE");
        String ville = resultSet.getString("VILLE");
        String numTelephone = resultSet.getString("NUM_TELEPHONE");
        String email = resultSet.getString("E_MAIL");
        double chiffreAffaire = resultSet.getDouble("CHFFREAFAIR");
        String commentaire = resultSet.getString("COMMENTAIRE");

        return new Client(idClient, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, email, commentaire, chiffreAffaire, nbrEmploye);}
       catch (MyException me) {
           throw new SQLException(me.getMessage());
       }
    }



}
