package controleur;

import exception.ControleurExcpetion;
import exception.DaoException;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Formulair;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Le contrôleur pour la gestion des formulaires dans l'application.
 */
public class ControleurFormulaire {

    /**
     * Initialise le formulaire avec le choix spécifié.
     *
     * @param choix le choix spécifié pour le formulaire.
     */
    public static void init(String choix) {
        Formulair formulaire = new Formulair(choix);
        formulaire.setVisible(true);
    }

    /**
     * Démarre l'interface d'accueil.
     */
    public static void startAccueil() {
        ControleurAccueil.init();
    }

    /**
     * Permet de choisir un client.
     *
     * @throws MyException si une exception générale se produit.
     * @throws SQLException si une erreur SQL se produit lors de l'accès à la base de données.
     * @throws ControleurExcpetion si une exception spécifique au contrôleur se produit.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     * @throws DaoException si une erreur DAO se produit.
     */
    public static void choixClient() throws Exception{
        ControleurAccueil.selectClient("clientVise");
    }

    /**
     * Permet de choisir un prospect.
     *
     * @throws MyException si une exception générale se produit.
     */
    public static void choixProspect() throws Exception {
        ControleurAccueil.selectProspect("prospectVise");
    }
    public static Client clientVise;
    public static Prospect prospectVise;

    //---------------------Create-----------
    public static void createClient(int idClient, String raisonSociale, String numRue, String nomRue,
                                    String codePostal, String telephone, String ville, String email,
                                    String commentaire, double chiffreAffaireStr, int nombreEmployesStr) throws Exception {



        Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaireStr, nombreEmployesStr);
        DaoClient.create(client);
    }

    public static void createProspect(int idProspect,
                                      String raisonSociale,
                                      String nomDeRue,
                                      String numDeRue,
                                      String codePostal,
                                      String telephone,
                                      String ville,
                                      String email,
                                      String commentaire,
                                      String dateDeProspection,
                                      String prospectInteresse)
            throws
            Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateProspection = LocalDate.parse(dateDeProspection, formatter);
        Prospect prospect = new Prospect(idProspect, raisonSociale, nomDeRue, numDeRue, codePostal, telephone,
                ville, email, commentaire, dateProspection, prospectInteresse);
        DaoProspect.create(prospect);
    }

    //-------------------------------Update-----------------------
    public static void updateClient(int idClient, String raisonSociale, String numRue, String nomRue,
                                    String codePostal, String telephone, String ville, String email,
                                    String commentaire, double chiffreAffaireStr, int nombreEmployesStr) throws Exception{

        double chiffreAffaire = Double.parseDouble(String.valueOf(chiffreAffaireStr));
        int nombreEmployes = Integer.parseInt(String.valueOf(nombreEmployesStr));

        Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email,
                commentaire, chiffreAffaire, nombreEmployes);
        DaoClient.update(client);
        clientVise = null;
    }

    public static void updateProspect(int idProspect, String raisonSociale, String numRue, String nomRue,
                                      String codePostal, String telephone, String ville, String email, String commentaire,
                                      String dateDeProspectionStr, String prospectInteresse) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateDeProspection = LocalDate.parse(dateDeProspectionStr, formatter);


        Prospect prospect = new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostal, telephone, ville,
                email, commentaire, dateDeProspection, prospectInteresse);
        DaoProspect.update(prospect);
    }

    //-------------------------------Delete-----------------------
    public static void deleteClient(Client clientVise) throws Exception{

        DaoClient.delete(clientVise.getId());
    }

    public static void deleteProspect(Prospect prospectVise) throws Exception{

        DaoProspect.delete(prospectVise.getId());
    }
}
