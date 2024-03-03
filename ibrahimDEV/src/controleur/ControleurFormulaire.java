package controleur;

import exception.MyException;
import model.dao.DaoClient;
import model.entite.Client;
import vue.Acceuil;
import vue.Formulair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControleurFormulaire {

    private Formulair vue;
    private Client client;

    public ControleurFormulaire(Client client) {
        if (client == null) {
            System.out.println("Client is null in constructor.");
        } else {
            System.out.println("Le Client n'est pas null en constructor.");
        }
        this.client = client;
        this.vue = new Formulair(client);
        initialiserEcouteurs();
    }

    private void initialiserEcouteurs() {
        this.vue.addValiderListener(new ValiderListener());
        this.vue.addAccueilListener(new AccueilListener());

    }

    private class ValiderListener implements ActionListener {
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

    private class AccueilListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gererAccueil();
        }
    }



    private void createClient() {
        System.out.println("createClient applé");
        if (this.client != null) {
            JOptionPane.showMessageDialog(null, "Un client existe déjà. Utilisez la fonction de mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idClient = vue.getTfId().getText();
        String raisonSociale = vue.getTfRaisonSociale().getText();
        String nomRue = vue.getTfNomRue().getText();
        String numRue = vue.getTfNumRue().getText();
        String codePostal = vue.getTfCodePostal().getText();
        String ville = vue.getTfVille().getText();
        String telephone = vue.getTfTelephone().getText();
        String email = vue.getTfEmail().getText();
        String chiffreAffaireStr = vue.getTfChiffreAffaire().getText();
        String nombreEmployesStr = vue.getTfNombreEmployes().getText();
        String commentaire = vue.getTfCommentaire().getText();

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
            JOptionPane.showMessageDialog(vue, "Client créé avec succès !");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Erreur de format pour le chiffre d'affaires ou le nombre d'employés", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vue, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
                    vue.updateClient(selectedClient);
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
                vue.getTfRaisonSociale().getText(), // Raison sociale
                vue.getTfNumRue().getText(), // Numéro de rue
                vue.getTfNomRue().getText(), // Nom de rue
                vue.getTfCodePostal().getText(), // Code postal
                vue.getTfTelephone().getText(), // Téléphone
                vue.getTfVille().getText(), // Ville
                vue.getTfEmail().getText(), // Email
                vue.getTfCommentaire().getText(), // Commentaire
                Double.parseDouble(vue.getTfChiffreAffaire().getText()), // Chiffre d'affaires
                Integer.parseInt(vue.getTfNombreEmployes().getText()) // Nombre d'employés
        );

        try {
            DaoClient.update(updatedClient);
            JOptionPane.showMessageDialog(vue, "Client mis à jour avec succès !");
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vue, "Erreur lors de la mise à jour : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void gererAccueil() {
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
        vue.dispose();
    }

    public void afficherTousLesClients() {
        ArrayList<Client> clients = null;
        try {
            clients = DaoClient.findAll();
        } catch (SQLException | IOException | MyException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de la liste des clients : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun client trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("Liste des clients :\n\n");
        for (Client client : clients) {
            message.append("ID: ").append(client.getId()).append("\n");
            message.append("Raison sociale: ").append(client.getRaisonSociale()).append("\n");
            message.append("Adresse: ").append(client.getNumeroDeRue()).append(" ").append(client.getNomDeRue()).append(", ").append(client.getCodePostal()).append(" ").append(client.getVille()).append("\n");
            message.append("Téléphone: ").append(client.getTelephone()).append("\n");
            message.append("Email: ").append(client.getemail()).append("\n");
            message.append("Chiffre d'affaires: ").append(client.getChiffreDaffaire()).append("\n");
            message.append("Nombre d'employés: ").append(client.getNombreEmployer()).append("\n");
            message.append("Commentaire: ").append(client.getCommentaire()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Liste des clients", JOptionPane.INFORMATION_MESSAGE);
    }
    public void selectClientToDelete() {
        if (this.client == null || !(this.client instanceof Client)) {
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

}