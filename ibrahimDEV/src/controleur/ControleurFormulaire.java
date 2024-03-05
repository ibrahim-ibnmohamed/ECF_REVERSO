package controleur;

import exception.ControleurExcpetion;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Formulair;
import vue.FormulairProspect;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ControleurFormulaire {



    public static void init(String choix) throws MyException {
        Formulair formulair= new Formulair(choix);
        formulair.setVisible(true);

    }

    public static void startAccueil(){
        ControleurAccueil.init();

    }

    public static void choixClient() throws MyException, SQLException, ControleurExcpetion, IOException {
        ControleurAccueil.selectClient();
    }
    public static void choixProspect() throws MyException {
        ControleurAccueil.selectProspect();
    }

    private static Formulair vueClient;
    private FormulairProspect vueProspect;
    public static Client clientVise ;
    public static Prospect prospectVise ;

    //---------------------Create-----------
    public static void createClient(int idClient, String raisonSociale, String numRue, String nomRue,
                                    String codePostal, String telephone, String ville, String email,
                                    String commentaire, double chiffreAffaireStr, int nombreEmployesStr) throws MyException, SQLException, IOException,NullPointerException {

            double chiffreAffaire = chiffreAffaireStr;
            int nombreEmployes = nombreEmployesStr;

            Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
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
            MyException,
            SQLException,
            IOException,
            NullPointerException {
        System.out.println("createProspect appelé");


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateProspection = LocalDate.parse(dateDeProspection, formatter);
            Prospect prospect = new Prospect(idProspect, raisonSociale, nomDeRue, numDeRue, codePostal, telephone,
                    ville, email, commentaire, dateProspection, prospectInteresse);
            DaoProspect.create(prospect);
    }

    //-------------------------------Update-----------------------
    public static void updateClient( int idClient,String
            raisonSociale,String numRue,String nomRue,
                                     String codePostal,
                                     String telephone,
                                     String ville,
                                     String email,
                                     String commentaire,
                                     double chiffreAffaireStr,
                                     int nombreEmployesStr) throws MyException, SQLException, IOException,NullPointerException {

            double chiffreAffaire = Double.parseDouble(String.valueOf(chiffreAffaireStr));
            int nombreEmployes = Integer.parseInt(String.valueOf(nombreEmployesStr));

            Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email,
                    commentaire, chiffreAffaire, nombreEmployes);
            DaoClient.update(client);
            clientVise = null;
    }
    public static void updateProspect(int idProspect, String raisonSociale, String numRue, String nomRue,
                                      String codePostal, String telephone, String ville, String email, String commentaire,
                                      String dateDeProspectionStr, String prospectInteresseStr) throws MyException,
            SQLException, IOException,NullPointerException {


            LocalDate dateDeProspection = LocalDate.parse(dateDeProspectionStr);
            String prospectInteresse = (prospectInteresseStr);

            Prospect prospect = new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostal, telephone, ville,
                    email, commentaire, dateDeProspection, prospectInteresse);
            DaoProspect.update(prospect);
    }
    //-------------------------------Delete-----------------------
    public static void deleteClient(Client clientVise) throws MyException, SQLException, IOException,NullPointerException {

        DaoClient.delete(clientVise.getId());
    }

    public static void deleteProspect(Prospect prospectVise) throws MyException, SQLException, IOException,NullPointerException {

        DaoProspect.delete(ControleurFormulaire.prospectVise.getId());
    }
}