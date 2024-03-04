package application;

import controleur.ControleurAccueil;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoConnection;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Formulair;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

        String dateUser = "31/03/2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateUser, formatter);
        Prospect prospect1 = new Prospect(1,
                "Yahoo",
                "51",
                "rue d'alsace'",
                "54100", "0733232315",
                "nancy", "google@hhh.com",
                "le client est interesé",
                date,
                "yes");

       // System.out.println(prospect1);
        Client client = new Client(2,
                "ibrar",
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
        // DaoProspect.create(prospect1);
      //  prospect.setId(2); // ID du client à mettre à jour
      //  prospect.setRaisonSociale("PHP codeur");
      //  prospect.setNomDeRue("1 rue de chifre dix ");

        try {
            Connection connection = new DaoConnection().getConnection();
            if (connection != null) {
                System.out.println("Connexion réussie à la base de données");

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
        //System.out.println(DaoProspect.findAll());
        System.out.println(DaoProspect.findByName("EPTECH"));
        // DaoProspect.delete(4);
        // DaoProspect.update(prospect);


        ControleurAccueil.init();

     // Formulair formulair=new Formulair();
       // formulair.setVisible(true);
    }


}
