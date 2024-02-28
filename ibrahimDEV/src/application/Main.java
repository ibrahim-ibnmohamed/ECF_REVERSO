package application;

import exception.MyException;
import model.dao.DaoConnection;
import model.entite.Prospect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public Main() throws SQLException, IOException {
    }

    public static void main(String[] args) throws MyException {
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
                Prospect.ProspectInteresse.OUI);

        System.out.println(prospect);

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
    }
}
