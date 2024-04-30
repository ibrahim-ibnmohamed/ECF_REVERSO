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
import java.util.logging.Logger;

/**
 * La classe {@code DaoConnection} est responsable de l'établissement de la connexion avec la base de données.
 * Elle utilise un fichier de propriétés pour obtenir les informations de connexion.
 */
public class DaoConnection {

    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DaoConnection.class.getName());

    /**
     * Méthode privée pour établir la connexion avec la base de données en utilisant les informations du fichier de propriétés.
     *
     * @throws SQLException  Si une erreur SQL se produit lors de l'établissement de la connexion.
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de propriétés.
     */
    private static void connect() throws Exception {
        Properties dataProperties = new Properties();
        File file = new File("database.properties");

        try (FileInputStream input = new FileInputStream(file)) {
            dataProperties.load(input);

        String url = dataProperties.getProperty("url");
        String username = dataProperties.getProperty("username");
        String password = dataProperties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            MyLogger.LOGGER.log(Level.INFO,"Connexion à la base de données établie avec succès.");
        } catch (SQLException|IOException e) {
            MyLogger.LOGGER.log(Level.SEVERE, "Erreur lors de l'établissement de la connexion à la base de données", e);
            throw new SQLException("Erreur lors de l'établissement de la connexion à la base de données", e);
        }
    }
    /**
     * Méthode statique pour obtenir la connexion à la base de données.
     *
     * @return La connexion à la base de données.
     * @throws SQLException  Si une erreur SQL se produit lors de l'établissement de la connexion.
     * @throws IOException   Si une erreur d'entrée/sortie se produit lors de la lecture du fichier de propriétés.
     */
    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {

                connect();
        }
        return connection;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                if (connection != null) {
                    try {
                        connection.close();
                        MyLogger.LOGGER.info("Connexion à la base de données fermée avec succès.");
                    } catch (SQLException e) {
                        MyLogger.LOGGER.log(Level.SEVERE, "Erreur lors de la fermeture de la connexion à la base de données", e);
                    }
                }
            }
        });
    }
}
