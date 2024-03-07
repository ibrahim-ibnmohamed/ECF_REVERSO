package model.dao;

import utilitaires.MyLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

/**
 * La classe {@code DaoConnection} est responsable de l'établissement de la connexion avec la base de données.
 * Elle utilise un fichier de propriétés pour obtenir les informations de connexion.
 */
public class DaoConnection {

    private static Connection connection;

    /**
     * Méthode statique qui ajoute un hook de fermeture pour fermer la connexion à la base de données lors de l'arrêt de l'application.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (connection != null) {
                    try {
                        MyLogger.LOGGER.info("Fermeture de la connexion à la base de données.");
                        connection.close();
                    } catch (SQLException e) {
                        MyLogger.LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                }
            }
        });
    }

    /**
     * Méthode privée pour établir la connexion avec la base de données en utilisant les informations du fichier de propriétés.
     *
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de propriétés.
     * @throws SQLException  Si une erreur SQL se produit lors de l'établissement de la connexion.
     */
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

    /**
     * Méthode statique pour obtenir la connexion à la base de données.
     *
     * @return La connexion à la base de données.
     * @throws SQLException  Si une erreur SQL se produit lors de l'établissement de la connexion.
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de propriétés.
     */
    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            connecter();
        }
        return connection;
    }
}
