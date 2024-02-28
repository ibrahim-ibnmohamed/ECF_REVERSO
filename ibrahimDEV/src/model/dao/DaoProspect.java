package model.dao;

import exception.MyException;
import model.entite.Prospect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DaoProspect {

        public static ArrayList ProspectFindAll() throws SQLException, IOException, MyException {

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
        }



    }


