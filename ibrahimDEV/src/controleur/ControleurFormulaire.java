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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;



public class ControleurFormulaire {

    private Formulair vueClient;
    private FormulairProspect vueProspect;
    private Client client;
    private Prospect prospect;
    public ControleurFormulaire(Client client) {
        System.out.println("ControleurFormulaire applé");
        this.client = client;

            this.vueClient = new Formulair(client);
            initialiserEcouteursClient();

    }

    public ControleurFormulaire(Prospect prospect) {
        System.out.println("ControleurFormulaire applé");
        this.prospect = prospect;


            this.vueProspect = new FormulairProspect(prospect);
            initialiserEcouteursProspect();

    }







    private void initialiserEcouteursClient() {
        this.vueClient.addValiderListener(new ValiderListenerClient());
        this.vueClient.addAccueilListener(new AccueilListener());

    }

    private void initialiserEcouteursProspect() {
        this.vueProspect.addValiderListener(new ValiderListenerProspect());
        this.vueProspect.addAccueilListener(new AccueilListener());
    }


    private class ValiderListenerClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (client == null) {
                createClient();
            } else {
                try {
                    updateClient();
                } catch (MyException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private class ValiderListenerProspect implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (client == null) {
                createProspect();
            } else {
                try {
                    updateProspect();
                } catch (MyException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    public void createProspect() {
        System.out.println("createProspect applé");
        if (this.prospect != null) {
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

    //--------------------------------
    private class AccueilListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gererAccueil();
            vueClient.dispose();
            vueProspect.dispose();

        }
    }



    public void createClient() {
        System.out.println("createClient applé");
        if (this.client != null) {
            JOptionPane.showMessageDialog(null, "Un client existe déjà. Utilisez la fonction de mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idClient = vueClient.getTfId().getText();
        String raisonSociale = vueClient.getTfRaisonSociale().getText();
        String nomRue = vueClient.getTfNomRue().getText();
        String numRue = vueClient.getTfNumRue().getText();
        String codePostal = vueClient.getTfCodePostal().getText();
        String ville = vueClient.getTfVille().getText();
        String telephone = vueClient.getTfTelephone().getText();
        String email = vueClient.getTfEmail().getText();
        String chiffreAffaireStr = vueClient.getTfChiffreAffaire().getText();
        String nombreEmployesStr = vueClient.getTfNombreEmployes().getText();
        String commentaire = vueClient.getTfCommentaire().getText();

        if (idClient.isEmpty() || raisonSociale.isEmpty() || nomRue.isEmpty() || numRue.isEmpty() ||
                codePostal.isEmpty() || ville.isEmpty() || telephone.isEmpty() || email.isEmpty() ||
                chiffreAffaireStr.isEmpty() || nombreEmployesStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idClient);
            double chiffreAffaire = Double.parseDouble(chiffreAffaireStr);
            int nombreEmployes = Integer.parseInt(nombreEmployesStr);

            Client client = new Client(id, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
            DaoClient.create(client);
            JOptionPane.showMessageDialog(vueClient, "Client créé avec succès !");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vueClient, "Erreur de format pour le chiffre d'affaires ou le nombre d'employés", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueClient, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void selectClientToUpdate() throws MyException {
        System.out.println("selectClientToUpdate appelé ");
        if (this.client == null) {
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
                    this.client = selectedClient; // Assigner le client sélectionné à this.client
                    vueClient.updateClient(selectedClient);
                } else {
                    JOptionPane.showMessageDialog(null, "Client non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Si un client est déjà sélectionné, appeler directement updateClient()
            try {
                updateClient();
            } catch (MyException ex) {
                throw new RuntimeException(ex);
            }
        }
    }




    private void updateClient() throws MyException {
        System.out.println("update applé ");
        // Créer une nouvelle variable locale pour l'objet mis à jour

        Client updatedClient = new Client(
                client.getId(), // ID
                vueClient.getTfRaisonSociale().getText(), // Raison sociale
                vueClient.getTfNumRue().getText(), // Numéro de rue
                vueClient.getTfNomRue().getText(), // Nom de rue
                vueClient.getTfCodePostal().getText(), // Code postal
                vueClient.getTfTelephone().getText(), // Téléphone
                vueClient.getTfVille().getText(), // Ville
                vueClient.getTfEmail().getText(), // Email
                vueClient.getTfCommentaire().getText(), // Commentaire
                Double.parseDouble(vueClient.getTfChiffreAffaire().getText()), // Chiffre d'affaires
                Integer.parseInt(vueClient.getTfNombreEmployes().getText()) // Nombre d'employés
        );

        try {
            DaoClient.update(updatedClient);
            JOptionPane.showMessageDialog(vueClient, "Client mis à jour avec succès !");
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueClient, "Erreur lors de la mise à jour : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

//--------------------------------

    public void selectProspctToUpdate() throws MyException {
        System.out.println("selectProspectToUpdate appelé ");
        if (this.prospect == null) {
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
                    this.prospect = selectedProspect; // Assigner le prospect sélectionné à this.prospect
                    vueProspect.updateProspect(selectedProspect);
                } else {
                    JOptionPane.showMessageDialog(null, "Prospect non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Si un prospect est déjà sélectionné, appeler directement updateProspect()
            try {
                updateProspect();
            } catch (MyException ex) {
                throw new RuntimeException(ex);
            }
        }
    }




    private void updateProspect() throws MyException {
        // Récupérer le texte saisi par l'utilisateur pour la date de prospection
        String dateDeProspectionText = vueProspect.getTfDateDeProspection().getText();
        LocalDate dateDeProspection = null;
        // Vérifier si un texte a été saisi
        if (!dateDeProspectionText.isEmpty()) {
            // Convertir le texte en LocalDate
            try {
                dateDeProspection = LocalDate.parse(dateDeProspectionText);
            } catch (DateTimeParseException e) {
                // Gérer l'exception si le format de la date est incorrect
                e.printStackTrace();
                throw new MyException("Format de date incorrect pour la date de prospection");
            }
        }



        // Créer un nouvel objet Prospect avec les informations mises à jour
        Prospect updatedProspect = new Prospect(
                prospect.getId(), // ID
                vueProspect.getTfRaisonSociale().getText(), // Raison sociale
                vueProspect.getTfNumRue().getText(), // Numéro de rue
                vueProspect.getTfNomRue().getText(), // Nom de rue
                vueProspect.getTfCodePostal().getText(), // Code postal
                vueProspect.getTfTelephone().getText(), // Téléphone
                vueProspect.getTfVille().getText(), // Ville
                vueProspect.getTfEmail().getText(), // Email
                vueProspect.getTfCommentaire().getText(), // Commentaire
                dateDeProspection, // Date de prospection
                vueProspect.getTfProspectInteresse().getText()// Prospect intéressé
        );

        try {
            DaoProspect.update(updatedProspect);
            JOptionPane.showMessageDialog(vueProspect, "Prospect mis à jour avec succès !");
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vueProspect, "Erreur lors de la mise à jour : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    //----------------------------------

    public void gererAccueil() {
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
        vueClient.dispose();
        vueProspect.dispose();
    }

    public void selectClientToDelete() {
        if (this.client == null ) {
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
//-------------------------------
    public void selectProcpectToDelete() {
        if (this.prospect == null ) {
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

}