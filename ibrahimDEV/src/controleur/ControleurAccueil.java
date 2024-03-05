package controleur;

import exception.ControleurExcpetion;
import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Acceuil;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControleurAccueil {



    private static Acceuil acceuil;
    public static void init(){
        acceuil= new Acceuil();
       acceuil.setVisible(true);
    }
     public static void startFormulaire(String string) throws MyException {
        ControleurFormulaire.init(string);
     }

    public static void startAffichage(String string) {
        ControleurAffichage.init(string);
    }

    //---------------------select-----------

    public static  void selectClient() throws MyException, SQLException, IOException, ControleurExcpetion {

        ArrayList<Client> clients = null;

        clients = DaoClient.findAll();

        // Vérifier si la liste des clients est vide
        if (clients.isEmpty()) {
            throw new ControleurExcpetion("la liste Client est vide ");
        }
        // Créer un tableau de noms de clients pour la boîte de dialogue
        String[] clientNames = new String[clients.size()];
        for (int i = 0; i < clients.size(); i++) {
            clientNames[i] = clients.get(i).getRaisonSociale();
        }
        // Afficher la boîte de dialogue pour sélectionner un client
        String selectedClientName = (String) JOptionPane.showInputDialog(null, "Choisissez un client à mettre à jour :", "Choix du client", JOptionPane.QUESTION_MESSAGE, null, clientNames, clientNames[0]);
        if (selectedClientName != null) {
            // Rechercher le client sélectionné dans la liste des clients
            Client selectedClient = null;
            for (Client client : clients) {
                if (client.getRaisonSociale().equals(selectedClientName)) {
                    selectedClient = client;
                    break;
                }
            }
            // Vérifier si le client sélectionné a été trouvé
            if (selectedClient != null) {
                // Mettre à jour le formulaire avec les informations du client sélectionné
                ControleurFormulaire.clientVise = selectedClient; // Assigner le client sélectionné

            } else {
                throw new ControleurExcpetion(" le client n'a pas été trouvé");
            }
        }
    }
    public static void selectProspect() throws MyException {
        System.out.println("selectProspect appelé ");

        ArrayList<Prospect> prospects = null;
        try {
            prospects = DaoProspect.findAll();
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de la liste des prospects : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier si la liste des prospects est vide
        if (prospects.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun prospect trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer un tableau de noms de prospects pour la boîte de dialogue
        String[] prospectNames = new String[prospects.size()];
        for (int i = 0; i < prospects.size(); i++) {
            prospectNames[i] = prospects.get(i).getRaisonSociale();
        }

        // Afficher la boîte de dialogue pour sélectionner un prospect
        String selectedProspectName = (String) JOptionPane.showInputDialog(null, "Choisissez un prospect à mettre à jour :", "Choix du prospect", JOptionPane.QUESTION_MESSAGE, null, prospectNames, prospectNames[0]);
        if (selectedProspectName != null) {
            // Rechercher le prospect sélectionné dans la liste des prospects
            Prospect selectedProspect = null;
            for (Prospect prospect : prospects) {
                if (prospect.getRaisonSociale().equals(selectedProspectName)) {
                    selectedProspect = prospect;
                    break;
                }
            }
            // Vérifier si le prospect sélectionné a été trouvé
            if (selectedProspect != null) {
                // Mettre à jour le formulaire avec les informations du prospect sélectionné
                ControleurFormulaire.prospectVise = selectedProspect; // Assigner le prospect sélectionné
            } else {
                JOptionPane.showMessageDialog(null, "Prospect non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
