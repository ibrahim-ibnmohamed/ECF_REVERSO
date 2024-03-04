package controleur;

import exception.MyException;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;
import vue.Acceuil;
import vue.Formulair;
import vue.FormulairProspect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;



public class ControleurFormulaire {


    public static void init(String choix) throws MyException {
        Formulair formulair= new Formulair(choix);
        formulair.setVisible(true);


    }

    private static Formulair vueClient;
    private FormulairProspect vueProspect;
    public static Client clientVise;
    public static Prospect prospectVise;
    public ControleurFormulaire(Client client) throws MyException {
        System.out.println("ControleurFormulaire applé");
        clientVise = client;

            this.vueClient = new Formulair("client");


    }



    //---------------------Create-----------
    public static void createClient(int idClient,String
            raisonSociale,String numRue,String nomRue,
                                    String codePostal,
                                    String telephone,
                                    String ville,
                                    String email,
                                    String commentaire,
                                    double chiffreAffaireStr,
                                    int nombreEmployesStr) {
        System.out.println("createClient applé");

        try {

            double chiffreAffaire = Double.parseDouble(String.valueOf(chiffreAffaireStr));
            int nombreEmployes = Integer.parseInt(String.valueOf(nombreEmployesStr));

            Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
            DaoClient.create(client);
            JOptionPane.showMessageDialog(vueClient, "Client créé avec succès !");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vueClient, "Erreur de format pour le chiffre d'affaires ou le nombre d'employés", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueClient, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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
                                      String prospectInteresse) {
        System.out.println("createProspect appelé");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateProspection = LocalDate.parse(dateDeProspection, formatter);
            Prospect prospect = new Prospect(idProspect, raisonSociale, nomDeRue, numDeRue, codePostal, telephone, ville, email, commentaire, dateProspection, prospectInteresse);
            DaoProspect.create(prospect);
            JOptionPane.showMessageDialog(vueClient, "Prospect créé avec succès !");
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueClient, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

   //---------------------select-----------

    public static  void selectClient() throws MyException {
        System.out.println("selectClientToUpdate appelé ");
        if (clientVise == null) {
            ArrayList<Client> clients = null;
            try {
                clients = DaoClient.findAll();
            } catch (SQLException | IOException | MyException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de la liste des clients : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier si la liste des clients est vide
            if (clients.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun client trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
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
                    clientVise= selectedClient; // Assigner le client sélectionné à this.client

                } else {
                    JOptionPane.showMessageDialog(null, "Client non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void selectProspect() throws MyException {
        System.out.println("selectProspect appelé ");
        if (prospectVise == null) {
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
                    prospectVise = selectedProspect; // Assigner le prospect sélectionné à this.prospect
                } else {
                    JOptionPane.showMessageDialog(null, "Prospect non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
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
                                     int nombreEmployesStr) throws MyException {
        System.out.println("createClient applé");

        try {

            double chiffreAffaire = Double.parseDouble(String.valueOf(chiffreAffaireStr));
            int nombreEmployes = Integer.parseInt(String.valueOf(nombreEmployesStr));

            Client client = new Client(idClient, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
            DaoClient.update(client);
            clientVise = null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProspect(int idProspect, String raisonSociale, String numRue, String nomRue,
                                      String codePostal, String telephone, String ville, String email, String commentaire,
                                      String dateDeProspectionStr, String prospectInteresseStr) throws MyException {
        System.out.println("updateProspect appelé");

        try {
            LocalDate dateDeProspection = LocalDate.parse(dateDeProspectionStr);
            String prospectInteresse = (prospectInteresseStr);

            Prospect prospect = new Prospect(idProspect, raisonSociale, numRue, nomRue, codePostal, telephone, ville,
                    email, commentaire, dateDeProspection, prospectInteresse);
            DaoProspect.update(prospect);
            prospectVise= null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //-------------------------------Delete-----------------------
    public static void deleteClient(Client clientVise) throws MyException, SQLException, IOException {

        DaoClient.delete(clientVise.getId());
    }

    public static void deleteProspect(Prospect prospectVise) throws MyException, SQLException, IOException {

        DaoProspect.delete(ControleurFormulaire.prospectVise.getId());
    }


//----------------------a supprimer ---------

    public void gererAccueil() {
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
        vueClient.dispose();
        vueProspect.dispose();
    }

    public void selectClientToDelete() {
        if (clientVise == null ) {
            ArrayList<Client> clients;
            try {
                clients = DaoClient.findAll();
            } catch (SQLException | IOException | MyException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de la liste des clients : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier si la liste des clients est vide
            if (clients.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun client trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Créer un tableau de noms de clients pour la boîte de dialogue
            String[] clientNames = new String[clients.size()];
            for (int i = 0; i < clients.size(); i++) {
                clientNames[i] = clients.get(i).getRaisonSociale();
            }

            // Afficher la boîte de dialogue pour sélectionner un client
            String selectedClientName = (String) JOptionPane.showInputDialog(null, "Choisissez un client à supprimer :", "Choix du client", JOptionPane.QUESTION_MESSAGE, null, clientNames, clientNames[0]);
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
                    // Demander confirmation avant de supprimer le client
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce client ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        try {
                            DaoClient.delete(selectedClient.getId());
                            JOptionPane.showMessageDialog(null, "Client supprimé avec succès !");
                        } catch (SQLException | IOException | MyException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du client : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Client non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Un client est déjà sélectionné. Vous ne pouvez pas supprimer un client en cours de modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void selectProcpectToDelete() {
        if (prospectVise== null ) {
            ArrayList<Prospect> prospects;
            try {
                prospects = DaoProspect.findAll();
            } catch (SQLException | IOException | MyException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de la liste des procpects : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier si la liste des clients est vide
            if (prospects.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun procpecte trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Créer un tableau de noms de clients pour la boîte de dialogue
            String[] prospectNames = new String[prospects.size()];
            for (int i = 0; i < prospects.size(); i++) {
                prospectNames[i] = prospects.get(i).getRaisonSociale();
            }

            // Afficher la boîte de dialogue pour sélectionner un client
            String selectedProspectName = (String) JOptionPane.showInputDialog(null, "Choisissez un Prospecte à supprimer :", "Choix du Prospecte", JOptionPane.QUESTION_MESSAGE, null, prospectNames, prospectNames[0]);
            if (selectedProspectName != null) {
                // Rechercher le prospecte sélectionné dans la liste des clients
                Prospect selectedProspect = null;
                for (Prospect prospect : prospects) {
                    if (prospect.getRaisonSociale().equals(selectedProspectName)) {
                        selectedProspect = prospect;
                        break;
                    }
                }

                // Vérifier si le client sélectionné a été trouvé
                if (selectedProspect!= null) {
                    // Demander confirmation avant de supprimer le client
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce precpecte ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        try {
                            DaoProspect.delete(selectedProspect.getId());
                            JOptionPane.showMessageDialog(null, "Prosoecte supprimé avec succès !");
                        } catch (SQLException | IOException | MyException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du prospecte: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Prospecte non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Un prespecte est déjà sélectionné. Vous ne pouvez pas supprimer un client en cours de modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void createProspect() {
        System.out.println("createProspect applé");
        if (prospectVise != null) {
            JOptionPane.showMessageDialog(null, "Un Prospect existe déjà. Utilisez la fonction de mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idProspect = vueProspect.getTfId().getText();
        String raisonSociale = vueProspect.getTfRaisonSociale().getText();
        String nomRue = vueProspect.getTfNomRue().getText();
        String numRue = vueProspect.getTfNumRue().getText();
        String codePostal = vueProspect.getTfCodePostal().getText();
        String ville = vueProspect.getTfVille().getText();
        String telephone = vueProspect.getTfTelephone().getText();
        String email = vueProspect.getTfEmail().getText();
        String prospectInteresse = vueProspect.getTfProspectInteresse().getText();
        String dateDeProspectionText = vueProspect.getTfDateDeProspection().getText();
        LocalDate dateDeProspection = null;
        if (!dateDeProspectionText.isEmpty()) {
            try {
                dateDeProspection = LocalDate.parse(dateDeProspectionText);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Format de date invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }


        String commentaire = vueProspect.getTfCommentaire().getText();

        if (idProspect.isEmpty() || raisonSociale.isEmpty() || nomRue.isEmpty() || numRue.isEmpty() ||
                codePostal.isEmpty() || ville.isEmpty() || telephone.isEmpty() || email.isEmpty() ||
                dateDeProspection == null || prospectInteresse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idProspect);

            Prospect prospect = new Prospect(id, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, dateDeProspection, prospectInteresse);
            DaoProspect.create(prospect);
            JOptionPane.showMessageDialog(vueClient, "Prospect créé avec succès !");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vueClient, "Erreur de format pour le numéro d'identification.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueClient, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class AccueilListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gererAccueil();
            vueClient.dispose();
            vueProspect.dispose();

        }
    }


}