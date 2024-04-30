package model.dao;

import exception.DaoException;
import exception.MyException;
import model.entite.Prospect;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * La classe {@code DaoProspect} fournit les méthodes pour interagir avec la table "prospect" dans la base de données.
 */
public class DaoProspect {

    /**
     * Récupère tous les prospects de la base de données.
     *
     * @return Une liste de tous les prospects.
     * @throws DaoException Si une erreur spécifique au DAO se produit.
     */
    public static ArrayList<Prospect> findAll() throws Exception {
        try (Connection connection = DaoConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM prospect")) {

            ArrayList<Prospect> prospects = new ArrayList<>();

            while (resultSet.next()) {
                Prospect prospect = extractProspectFromResultSet(resultSet);
                prospects.add(prospect);
            }

            return prospects;
        } catch (SQLException e) {
            throw new SQLException("Problème rencontré lors de la recherche de tous les prospects."+e.getMessage());
        }catch(IOException ioe){
            throw new DaoException("aucun Prospect a été trouvé ") ;
        }
    }

    /**
     * Récupère un prospect par son nom.
     *
     * @param name Le nom du prospect à rechercher.
     * @return Le prospect correspondant au nom spécifié, null s'il n'existe pas.
     * @throws DaoException Si une erreur spécifique au DAO se produit.
     */
    public static Prospect findByName(String name) throws Exception {
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM prospect WHERE RAISON_SOCIALE = ?")) {

            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractProspectFromResultSet(resultSet);
                }
            } catch (MyException e) {
                throw new MyException("Problème lors de la récupération du prospect depuis la base de données.");
            }

            return null; // Retourner null si aucun prospect n'est trouvé avec ce nom
        } catch (SQLException | IOException e) {
            throw new DaoException("Problème rencontré lors de la recherche de Prospect.");
        } catch (MyException e) {
            throw new MyException  ("Problème rencontré lors de la recherche de prospect par nom.");
        }
    }

    /**
     * Crée un nouveau prospect dans la base de données.
     *
     * @param prospect Le prospect à créer.
     * @throws DaoException Si une erreur spécifique au DAO se produit.
     */
    public static void create(Prospect prospect) throws Exception {



        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO prospect (RAISON_SOCIALE, NOM_RUE, NUM_RUE, CODE_POSTALE, VILLE, NUM_TELEPHONE, E_MAIL, DATE_PROSPECT, COMMENTAIRE, INTERESSE) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

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
        } catch (SQLException e) {
            if (e.getErrorCode() ==1062 ){
                throw new DaoException("La raison sociale '" + prospect.getRaisonSociale() + "' existe déjà dans la base de données.");
            }else if (e.getErrorCode()==1406){
                throw new DaoException("le numero de rue'" + prospect.getNumeroDeRue() + "est trop longe .");
            }else {
                throw new SQLException("erreur creat Prospect"+e.getMessage()+e.getErrorCode());
            }
        }

    }


    /**
     * Supprime un prospect de la base de données.
     *
     * @param idProspect L'ID du prospect à supprimer.
     * @throws DaoException Si une erreur spécifique au DAO se produit.
     */
    public static void delete(int idProspect) throws Exception {
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM prospect WHERE ID_PROSPECT= ?")) {

            preparedStatement.setInt(1, idProspect);
            preparedStatement.executeUpdate();
        } catch ( IOException e) {
            throw new DaoException("Problème rencontré lors de la tentative de supprimer un client dans la base de données.");
        }catch (SQLException e){
            throw new DaoException("Problème rencontré lors de la tentative de supprimer un client dans la base de données."+e.getMessage());

        }
    }

    /**
     * Met à jour les informations d'un prospect dans la base de données.
     *
     * @param prospect Le prospect avec les informations mises à jour.
     * @throws DaoException Si une erreur spécifique au DAO se produit.
     */
    public static void update(Prospect prospect) throws Exception {
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prospect SET RAISON_SOCIALE = ?, NOM_RUE = ?, NUM_RUE = ?, CODE_POSTALE = ?, VILLE = ?, " +
                     "NUM_TELEPHONE = ?, E_MAIL = ?,DATE_PROSPECT = ?, COMMENTAIRE = ?, INTERESSE = ? WHERE ID_PROSPECT= ?")) {

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
        } catch (SQLException e) {
            if (e.getErrorCode() ==1062 ){
                throw new DaoException("La raison sociale '" + prospect.getRaisonSociale() + "' existe déjà dans la base de données.");
            }else if (e.getErrorCode()==1406){
                throw new DaoException("le numero de rue'" + prospect.getNumeroDeRue() + "est trop longe .");
            }else {
                throw new SQLException("erreur creat Client "+e.getMessage()+e.getErrorCode());
            }
        }
    }

    /**
     * Extrait un prospect à partir du ResultSet.
     *
     * @param resultSet Le ResultSet à partir duquel extraire le prospect.
     * @return Le prospect extrait.
     * @throws SQLException Si une erreur SQL se produit.
     */
    private static Prospect extractProspectFromResultSet(ResultSet resultSet) throws SQLException, MyException {
        int idProspect = resultSet.getInt("ID_PROSPECT");
        String interesse = resultSet.getString("INTERESSE");
        String raisonSociale = resultSet.getString("RAISON_SOCIALE");
        String nomRue = resultSet.getString("NOM_RUE");
        String numRue = resultSet.getString("NUM_RUE");
        String codePostale = resultSet.getString("CODE_POSTALE");
        String ville = resultSet.getString("VILLE");
        String numTelephone = resultSet.getString("NUM_TELEPHONE");
        String eMail = resultSet.getString("E_MAIL");
        Date dateDeProspectionSql = resultSet.getDate("DATE_PROSPECT");
        LocalDate dateDeProspection = dateDeProspectionSql.toLocalDate();
        String commentaire = resultSet.getString("COMMENTAIRE");

        return new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostale, numTelephone, ville, eMail, commentaire, dateDeProspection, interesse);
    }

}
