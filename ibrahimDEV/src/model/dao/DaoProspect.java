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

/**
 * La classe {@code DaoProspect} fournit les méthodes pour interagir avec la table "prospect" dans la base de données.
 */
public class DaoProspect {

    /**
     * Récupère tous les prospects de la base de données.
     *
     * @return Une liste de tous les prospects.
     * @throws SQLException   Si une erreur SQL se produit.
     * @throws IOException    Si une erreur d'entrée/sortie se produit.
     * @throws MyException    Si une erreur générale se produit.
     * @throws DaoException  Si une erreur spécifique au DAO se produit.
     */
    public static ArrayList<Prospect> findAll() throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        Statement preparedStatement = null;
        String query = "SELECT " +
                "ID_PROSPECT, " +
                "INTERESSE, " +
                "RAISON_SOCIALE, " +
                "NOM_RUE, " +
                "NUM_RUE, " +
                "CODE_POSTALE, " +
                "VILLE, " +
                "NUM_TELEPHONE, " +
                "E_MAIL, " +
                "DATE_PROSPECT, " +
                "COMMENTAIRE " +
                "FROM prospect";

        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème lors de la récupération des prospects depuis la base de données.");
            throw new DaoException("Problème lors de la récupération des prospects depuis la base de données.");
        } else {
            preparedStatement = connection.createStatement();
            ResultSet rs = preparedStatement.executeQuery(query);
            ArrayList<Prospect> prospects = new ArrayList<>();
            while (rs.next()) {
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

                Prospect prospect = new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, dateDeProspection, interesse);
                prospects.add(prospect);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
            return prospects;
        }
    }

    /**
     * Récupère un prospect par son nom.
     *
     * @param name Le nom du prospect à rechercher.
     * @return Le prospect correspondant au nom spécifié.
     * @throws SQLException   Si une erreur SQL se produit.
     * @throws IOException    Si une erreur d'entrée/sortie se produit.
     * @throws MyException    Si une erreur générale se produit.
     * @throws DaoException  Si une erreur spécifique au DAO se produit.
     */
    public static Prospect findByName(String name) throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        PreparedStatement preparedStatement = null;
        String query = "SELECT " +
                "ID_PROSPECT, " +
                "INTERESSE, " +
                "RAISON_SOCIALE, " +
                "NOM_RUE, " +
                "NUM_RUE, " +
                "CODE_POSTALE, " +
                "VILLE, " +
                "NUM_TELEPHONE, " +
                "E_MAIL, " +
                "DATE_PROSPECT, " +
                "COMMENTAIRE " +
                "FROM prospect WHERE RAISON_SOCIALE = ?";

        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème lors de la récupération du prospect depuis la base de données.");
            throw new DaoException("Problème lors de la récupération du prospect depuis la base de données.");
        } else {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
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
            rs.close();
            preparedStatement.close();
            return prospect;
        }
    }

    /**
     * Crée un nouveau prospect dans la base de données.
     *
     * @param prospect Le prospect à créer.
     * @throws SQLException   Si une erreur SQL se produit.
     * @throws IOException    Si une erreur d'entrée/sortie se produit.
     * @throws MyException    Si une erreur générale se produit.
     * @throws DaoException  Si une erreur spécifique au DAO se produit.
     */
    public static void create(Prospect prospect) throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        String query = "INSERT INTO prospect (RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, NUM_TELEPHONE, E_MAIL, DATE_PROSPECT, COMMENTAIRE, INTERESSE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème lors de la création du prospect dans la base de données.");
            throw new DaoException("Problème lors de la création du prospect dans la base de données.");
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * Supprime un prospect de la base de données.
     *
     * @param idProspect L'ID du prospect à supprimer.
     * @throws SQLException   Si une erreur SQL se produit.
     * @throws IOException    Si une erreur d'entrée/sortie se produit.
     * @throws MyException    Si une erreur générale se produit.
     * @throws DaoException  Si une erreur spécifique au DAO se produit.
     */
    public static void delete(int idProspect) throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        String query = "DELETE FROM prospect WHERE ID_PROSPECT= ?";
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème lors de la suppression du prospect dans la base de données.");
            throw new DaoException("Problème lors de la suppression du prospect dans la base de données.");
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idProspect);
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * Met à jour les informations d'un prospect dans la base de données.
     *
     * @param prospect Le prospect avec les informations mises à jour.
     * @throws SQLException   Si une erreur SQL se produit.
     * @throws IOException    Si une erreur d'entrée/sortie se produit.
     * @throws MyException    Si une erreur générale se produit.
     * @throws DaoException  Si une erreur spécifique au DAO se produit.
     */
    public static void update(Prospect prospect) throws SQLException, IOException, MyException, DaoException {
        Connection connection = new DaoConnection().getConnection();
        String query = "UPDATE prospect SET RAISON_SOCIALE = ?, NOM_RUE = ?, NUM_RUE = ?, CODE_POSTALE = ?, VILLE = ?, " +
                "NUM_TELEPHONE = ?, E_MAIL = ?,DATE_PROSPECT = ?, COMMENTAIRE = ?, INTERESSE = ? WHERE ID_PROSPECT= ?";
        if (connection == null) {
            MyLogger.LOGGER.log(Level.INFO, "Problème lors de la mise à jour du prospect dans la base de données.");
            throw new DaoException("Problème lors de la mise à jour du prospect dans la base de données.");
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
                preparedStatement.executeUpdate();
            }
        }
    }
}
