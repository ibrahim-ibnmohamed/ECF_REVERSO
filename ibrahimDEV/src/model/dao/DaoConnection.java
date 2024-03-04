package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoConnection {

    private static Connection connection;

    private static void connecter() throws IOException, SQLException {
        Properties dataProperties = new Properties();
        File fichier = new File("database.Properties");
        try (FileInputStream input = new FileInputStream(fichier)) {
            dataProperties.load(input);
        }

        String url = dataProperties.getProperty("url");
        String username = dataProperties.getProperty("username");
        String password = dataProperties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);
    }

    // Méthode pour obtenir la connexion à la base de données
    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null|| connection.isClosed() ) {
            connecter();
        }
        return connection;
    }


}
