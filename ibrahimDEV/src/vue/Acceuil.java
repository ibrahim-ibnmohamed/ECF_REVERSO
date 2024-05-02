package vue;

import controleur.ControleurAccueil;
import exception.DaoException;
import entite.Client;
import entite.Prospect;
import utilitaires.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Cette classe représente la fenêtre d'accueil de l'application.
 * Elle permet à l'utilisateur de choisir différentes actions à effectuer
 * concernant les clients et les prospects.
 */

public class Acceuil extends JDialog {

    private JPanel panel1;// Panel principal de la fenêtre
    private JLabel lbTitre; // Titre de la fenêtre

    // Boutons déroulants pour les actions sur les clients et les prospects
    private JComboBox<String> clientBtnCombo;
    private JComboBox<String> prospectBtnCombo;

    /**
     * Constructeur de la classe Acceuil.
     * Initialise la fenêtre et ses composants.
     */
    public Acceuil() {
        // Configuration de la fenêtre
        setTitle("Accueil");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        // Action listener pour le bouton déroulant des actions sur les clients
        clientBtnCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choixSelectionne = (String) clientBtnCombo.getSelectedItem();

                // Traitement selon l'action sélectionnée
                switch (choixSelectionne) {
                    case "Create":
                        ControleurAccueil.startFormulaire("createClient");
                        dispose();
                        break;
                    case "Update":
                        try {
                            ArrayList<Client> clients = ControleurAccueil.findAllClient();
                            String[] clientNames = new String[clients.size()];
                            for (int i = 0; i < clients.size(); i++) {
                                clientNames[i] = clients.get(i).getRaisonSociale();
                            }
                            String selectedClientName = (String) JOptionPane.showInputDialog(null,
                                    "Choisissez un Client à mettre à jour :", "Choix du prospect",
                                    JOptionPane.QUESTION_MESSAGE, null, clientNames, clientNames[0]);

                            if (selectedClientName != null) {
                                ControleurAccueil.selectClient(selectedClientName);
                                ControleurAccueil.startFormulaire("updateClient");
                            } else {
                                ControleurAccueil.init();
                            }
                            dispose();

                        } catch (DaoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "désolé une erreur s'est produite");
                            MyLogger.LOGGER.log(Level.SEVERE, ex.getMessage());
                            System.exit(1);
                        }
                        break;
                    case "Delete":
                        try {
                            ArrayList<Client> clients = ControleurAccueil.findAllClient();
                            String[] clientNames = new String[clients.size()];
                            for (int i = 0; i < clients.size(); i++) {
                                clientNames[i] = clients.get(i).getRaisonSociale();

                            }

                            // Afficher la boîte de dialogue pour sélectionner un prospect
                            String selectedClientName = (String) JOptionPane.showInputDialog(null,
                                    "Choisissez un client à Supprimer :", "Choix du client",
                                    JOptionPane.QUESTION_MESSAGE, null, clientNames, clientNames[0]);

                            if (selectedClientName != null) {
                                ControleurAccueil.selectClient(selectedClientName);
                                ControleurAccueil.startFormulaire("deleteClient");
                            } else {
                                ControleurAccueil.init();
                            }

                        } catch (DaoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "désolé une erreur s'est produite");
                            MyLogger.LOGGER.log(Level.SEVERE, ex.getMessage());
                            System.exit(1);
                        }
                        dispose();
                        break;
                    case "Find":
                        ControleurAccueil.startAffichage("client");
                        dispose();
                        break;
                    default:
                        break;
                }
            }
        });
        // Action listener pour le bouton déroulant des actions sur les prospects
        prospectBtnCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String choixSelectionne = (String) prospectBtnCombo.getSelectedItem();
                // Traitement selon l'action sélectionnée

                switch (choixSelectionne) {
                    case "Create":
                        ControleurAccueil.startFormulaire("createProsecte");
                        dispose();

                        break;
                    case "Update":
                        try {
                            ArrayList<Prospect> prospects = ControleurAccueil.findAllProspect();
                            String[] prospectNames = new String[prospects.size()];
                            for (int i = 0; i < prospects.size(); i++) {
                                prospectNames[i] = prospects.get(i).getRaisonSociale();
                            }

                            // Afficher la boîte de dialogue pour sélectionner un prospect
                            String selectedProspectName = (String) JOptionPane.showInputDialog(null,
                                    "Choisissez un prospect à mettre à jour :", "Choix du prospect",
                                    JOptionPane.QUESTION_MESSAGE, null, prospectNames, prospectNames[0]);

                            if (selectedProspectName != null) {
                                ControleurAccueil.selectProspect(selectedProspectName);
                                ControleurAccueil.startFormulaire("updateProspect");
                            } else {
                                ControleurAccueil.init();
                            }

                        } catch (DaoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "désolé une erreur s'est produite");
                            MyLogger.LOGGER.log(Level.SEVERE, ex.getMessage());
                            System.exit(1);
                        }
                        dispose();
                        break;
                    case "Delete":

                        try {
                            ArrayList<Prospect> prospects = ControleurAccueil.findAllProspect();
                            String[] prospectNames = new String[prospects.size()];
                            for (int i = 0; i < prospects.size(); i++) {
                                prospectNames[i] = prospects.get(i).getRaisonSociale();
                            }
                            // Afficher la boîte de dialogue pour sélectionner un prospect
                            String selectedProspectName = (String) JOptionPane.showInputDialog(null,
                                    "Choisissez un prospect à supprimer:", "Choix du prospect",
                                    JOptionPane.QUESTION_MESSAGE, null, prospectNames, prospectNames[0]);

                            if (selectedProspectName != null) {
                                ControleurAccueil.selectProspect(selectedProspectName);
                                ControleurAccueil.startFormulaire("deleteProspect");
                            } else {
                                ControleurAccueil.init();
                            }

                        } catch (DaoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "désolé une erreur s'est produite");
                            MyLogger.LOGGER.log(Level.SEVERE, ex.getMessage());
                            System.exit(1);
                        }

                        dispose();
                        break;
                    case "Find":
                        ControleurAccueil.startAffichage("procpect");
                        dispose();
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Méthode appelée pour créer les composants de l'interface utilisateur.
     * Dans ce cas, elle crée un panel et deux boutons déroulants.
     */
    private void createUIComponents() {
        panel1 = new JPanel();// Création du panel principal
        clientBtnCombo = new JComboBox<>();// Création du bouton déroulant pour les actions sur les clients
        prospectBtnCombo = new JComboBox<>();// Création du bouton déroulant pour les actions sur les prospects
    }
}
