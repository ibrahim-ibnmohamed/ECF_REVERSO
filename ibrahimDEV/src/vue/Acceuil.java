package vue;

import controleur.ControleurAccueil;
import exception.ControleurExcpetion;
import exception.DaoException;
import exception.MyException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;



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

                        try {
                            ControleurAccueil.startFormulaire("createClient");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                        break;
                    case "Update":
                        try {
                            ControleurAccueil.selectClient();
                                JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                                ControleurAccueil.startFormulaire("updateClient");
                                dispose();

                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ControleurExcpetion ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (DaoException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Delete":
                        try {
                            ControleurAccueil.startFormulaire("deleteClient");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                        break;
                    case "Find" :

                        try {
                            ControleurAccueil.startAffichage("client");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (DaoException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
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
                        try {
                            ControleurAccueil.startFormulaire("createProsecte");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Create");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();

                        break;
                    case "Update":
                        try {
                            ControleurAccueil.selectProspect();
                                JOptionPane.showMessageDialog(Acceuil.this, "vous avez choisi Update");
                                ControleurAccueil.startFormulaire("updateProspect");
                                dispose();

                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Delete":

                        try {
                            ControleurAccueil.startFormulaire("deleteProspect");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }

                        dispose();
                        break;
                    case "Find" :

                        try {
                            ControleurAccueil.startAffichage("procpect");
                            JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (DaoException ex) {
                            throw new RuntimeException(ex);
                        }

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
