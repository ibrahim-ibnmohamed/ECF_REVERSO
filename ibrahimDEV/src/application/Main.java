package application;

import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoConnection;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


public class Main {
    public Main() throws SQLException, IOException {
    }

    public static void main(String[] args) throws MyException, SQLException, IOException {
        String valeurUser = "mohamed "+"a"+"peur";
        System.out.println("SELECT " +
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
                " FROM client WHERE RAISON_SOCIALE = '" + valeurUser );

        String dateUser = "02/02/2002";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateUser, formatter);
        Prospect prospect = new Prospect(1,
                "ibraTech",
                "5",
                "rue de la republique",
                "25541", "0733232315",
                "nancy", "Ibrahim@hhh.com",
                "",
                date,
                "noooon");

        System.out.println(prospect);
        Client client = new Client(3,
                "ibrahim",
                "6",
                "rue de Paris",
                "5000",
                "0454455663",
                "Nice",
                "adresse@mail.com",
                "je suis un robo",
                1000,
                5
        );
        client.setId(2); // ID du client à mettre à jour
        client.setRaisonSociale("Aljazeera");
        client.setNomDeRue("1 rue de qater");

        try {
            Connection connection = new DaoConnection().getConnection();
            if (connection != null) {
                System.out.println("Connexion réussie à la base de données");
                // You can perform database operations here
            } else {
                System.out.println("Problème de connexion à la base de données");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }


        DaoClient daoClient = new DaoClient();
        System.out.println(DaoClient.findAll());
        System.out.println(DaoClient.findByName("ibraTech"));
        DaoProspect daoProspect=new DaoProspect();
        System.out.println(DaoProspect.ProspectFindAll());
        //DaoClient.create(client);
        DaoClient.delete(1);
        DaoClient.update(client);
    }

}
