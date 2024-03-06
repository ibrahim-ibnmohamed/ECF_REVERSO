package model.dao;

import exception.DaoException;
import exception.MyException;

import model.entite.Prospect;
import utilitaires.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

public class DaoProspect {

        public static ArrayList findAll() throws SQLException, IOException, MyException, DaoException {

            Connection connection= new DaoConnection().getConnection();
            Statement preparedStatement = null;
            String query = "SELECT " +
                    "ID_PROSPECT, "+
                    " INTERESSE," +
                    "RAISON_SOCIALE," +
                    "NOM_RUE," +
                    "NUM_RUE," +
                    "CODE_POSTALE," +
                    "VILLE," +
                    "NUM_TELEPHONE," +
                    "E_MAIL," +
                    "DATE_PROSPECT," +
                    "COMMENTAIRE" +
                    " FROM prospect";

            if(connection==null){
                MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de trouver un prospect dans la base de données.");
                throw new DaoException("Il y a un problème rencontré lors de la tentative de trouver un  prospect dans la base de données.");
            }else {
            preparedStatement =connection.createStatement();
            ResultSet rs =  preparedStatement.executeQuery(query);
            ArrayList<Prospect> prospects = new ArrayList<>();
            while (rs.next()){

                int idProspect=rs.getInt("ID_PROSPECT");
                String interesse = rs.getString("INTERESSE");
                String raisonSociale = rs.getString("RAISON_SOCIALE");
                String nomRue = rs.getString("NOM_RUE");
                String numRue= rs.getString("NUM_RUE");
                String codePostale= rs.getString("CODE_POSTALE");
                String ville = rs.getString("VILLE");
                String numTelephone = rs.getString("NUM_TELEPHONE");
                String eMail = rs.getString("E_MAIL");
                Date dateDeProspectionSql = rs.getDate("DATE_PROSPECT");
                LocalDate dateDeProspection = ((java.sql.Date) dateDeProspectionSql).toLocalDate();
                String commentaire = rs.getString("COMMENTAIRE");




                Prospect prospect= new Prospect(idProspect,raisonSociale,numRue,nomRue,codePostale,numTelephone,ville,eMail,commentaire,dateDeProspection,interesse);
                prospects.add(prospect);
            }

            // Fermeture des ressources
            rs.close();
            preparedStatement.close();
            connection.close();
            return prospects;
        }}
        
        // --------findByName---------
        public static Prospect findByName(String name) throws SQLException, IOException, MyException, DaoException {
            Connection connection = new DaoConnection().getConnection();
            PreparedStatement preparedStatement = null;
            String query = "SELECT " +
                    "ID_PROSPECT, " +
                    " INTERESSE," +
                    "RAISON_SOCIALE," +
                    "NOM_RUE," +
                    "NUM_RUE," +
                    "CODE_POSTALE," +
                    "VILLE," +
                    "NUM_TELEPHONE," +
                    "E_MAIL," +
                    "DATE_PROSPECT," +
                    "COMMENTAIRE" +
                    " FROM prospect WHERE RAISON_SOCIALE = ?";

            if(connection==null){
                MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de trouver un prospectdans la base de données.");
                throw new DaoException("Il y a un problème rencontré lors de la tentative de trouver un  prospect dans la base de données.");
            }else {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name); // Set the parameter value
            ResultSet rs = preparedStatement.executeQuery();

            Prospect prospect = null;
            if (rs.next()) {
                int idProspect = rs.getInt("ID_PROSPECT");
                String interesse = rs.getString("INTERESSE");
                String raisonSociale = rs.getString("RAISON_SOCIALE");
                String nomRue = rs.getString("NOM_RUE");
                String numRue = rs.getString("NUM_RUE");
                String codePostale = rs.getString("CODE_POSTALE");
                String ville = rs.getString("VILLE");
                String numTelephone = rs.getString("NUM_TELEPHONE");
                String eMail = rs.getString("E_MAIL");
                Date dateDeProspectionSql = rs.getDate("DATE_PROSPECT");
                LocalDate dateDeProspection = ((java.sql.Date) dateDeProspectionSql).toLocalDate();
                String commentaire = rs.getString("COMMENTAIRE");

                prospect = new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, dateDeProspection, interesse);
            }

            // Fermeture des ressources
            rs.close();
            preparedStatement.close();


            return prospect; // Retourner null si aucun prospect n'est trouvé
        }}

        //----------------create----------------
        public static void create(Prospect prospect) throws SQLException, IOException, MyException, DaoException {
            // Établir la connexion à la base de données
            Connection connection = new DaoConnection().getConnection();

            // Requête SQL pour insérer un nouveau prospect
            String query = "INSERT INTO prospect (RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, NUM_TELEPHONE," +
                    " E_MAIL, DATE_PROSPECT, COMMENTAIRE, INTERESSE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            if(connection==null){
                MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de crée un prospectdans la base de données.");
                throw new DaoException("Il y a un problème rencontré lors de la tentative de crée un  prospect dans la base de données.");
            }else {

            // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution des valeurs aux paramètres de la déclaration
                preparedStatement.setString(1, prospect.getRaisonSociale());
                preparedStatement.setString(2, prospect.getNomDeRue());
                preparedStatement.setString(3, prospect.getNumeroDeRue());
                preparedStatement.setString(4, prospect.getCodePostal());
                preparedStatement.setString(5, prospect.getVille());
                preparedStatement.setString(6, prospect.getTelephone());
                preparedStatement.setString(7, prospect.getemail());
                preparedStatement.setDate(8, java.sql.Date.valueOf(prospect.getDateDeProspection()));
                preparedStatement.setString(9, prospect.getCommentaire());
                preparedStatement.setString(10, prospect.getProspectInteresse());
                // Exécution de la déclaration pour insérer le nouveau prospect dans la base de données
                preparedStatement.executeUpdate();
            }




        }}

        //--------Delete-------------
        public static void delete(int idProspect) throws SQLException, IOException, MyException, DaoException {
            // Établir la connexion à la base de données
            Connection connection = new DaoConnection().getConnection();

            // Requête SQL pour supprimer un prospect en fonction de son ID
            String query = "DELETE FROM prospect WHERE ID_PROSPECT= ?";

            if(connection==null){
                MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de supprimer un prospect dans la base de données.");
                throw new DaoException("Il y a un problème rencontré lors de la tentative de supprimer un  prospect dans la base de données.");
            }else{


                // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution de la valeur à paramètre de la déclaration
                preparedStatement.setInt(1, idProspect);

                // Exécution de la déclaration pour supprimer le prospect de la base de données
                preparedStatement.executeUpdate();

            }


        }}

    //--------Update-------------

    public static void update(Prospect prospect) throws SQLException, IOException, MyException, DaoException {
        // Établir la connexion à la base de données
        Connection connection = new DaoConnection().getConnection();

        // Requête SQL pour mettre à jour un prospect en fonction de son ID
        String query = "UPDATE prospect SET RAISON_SOCIALE = ?, NOM_RUE = ?, NUM_RUE = ?, CODE_POSTALE = ?, VILLE = ?, " +
                "NUM_TELEPHONE = ?, E_MAIL = ?,DATE_PROSPECT = ?, COMMENTAIRE = ?, INTERESSE = ? WHERE ID_PROSPECT= ?";
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Il y a un problème rencontré lors de la tentative de mise à jour du prospect dans la base de données.");
            throw new DaoException("Il y a un problème rencontré lors de la tentative de mise à jour du prospect dans la base de données.");
        } else {

            // Préparer la déclaration SQL avec des paramètres
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Attribution des valeurs aux paramètres de la déclaration

                preparedStatement.setString(1, prospect.getRaisonSociale());
                preparedStatement.setString(2, prospect.getNomDeRue());
                preparedStatement.setString(3, prospect.getNumeroDeRue());
                preparedStatement.setString(4, prospect.getCodePostal());
                preparedStatement.setString(5, prospect.getVille());
                preparedStatement.setString(6, prospect.getTelephone());
                preparedStatement.setString(7, prospect.getemail());
                preparedStatement.setDate(8, java.sql.Date.valueOf(prospect.getDateDeProspection()));
                preparedStatement.setString(9, prospect.getCommentaire());
                preparedStatement.setString(10, prospect.getProspectInteresse());
                preparedStatement.setInt(11, prospect.getId());


                // Exécution de la déclaration pour mettre à jour le precpect dans la base de données
                preparedStatement.executeUpdate();
            }


        }
    }
}


