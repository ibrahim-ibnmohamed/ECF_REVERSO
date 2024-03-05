package vue;

import controleur.ControleurAccueil;
import exception.ControleurExcpetion;
import exception.MyException;
import model.entite.Client;
import model.entite.Prospect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static model.dao.DaoClient.findByName;

public class Acceuil extends JDialog {

    private JPanel panel1;
    private JLabel lbTitre;

    private JComboBox<String> clientBtnCombo;
    private JComboBox<String> prospectBtnCombo;
    private ArrayList<Client> clients;

    public Acceuil() {
        setTitle("Accueil");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        clientBtnCombo.addActionListener(new ActionListener() {
            Client client = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                String choixSelectionne = (String) clientBtnCombo.getSelectedItem();
                Client selectedClient = null;
                try {
                    selectedClient = findByName(choixSelectionne);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (MyException ex) {
                    throw new RuntimeException(ex);
                }

                switch (choixSelectionne) {
                    case "Create":

                        try {
                            ControleurAccueil.startFormulaire("createClient");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                        break;
                    case "Update":
                        try {
                            ControleurAccueil.selectClient();
                                JOptionPane.showMessageDialog(Acceuil.this, "un vrai msg");
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
                        }



                        break;
                    case "Delete":
                        try {
                            ControleurAccueil.startFormulaire("deleteClient");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        dispose();
                        break;
                    case "Find" :
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
                        ControleurAccueil.startAffichage("client");
                        dispose();


                    default:
                        break;
                }
            }
        });

        prospectBtnCombo.addActionListener(new ActionListener() {
            Prospect prospect = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                String choixSelectionne = (String) prospectBtnCombo.getSelectedItem();

                switch (choixSelectionne) {
                    case "Create":
                        try {
                            ControleurAccueil.startFormulaire("createProsecte");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Create");
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
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        try {
                            ControleurAccueil.startFormulaire("deleteProspect");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }

                        dispose();
                        break;
                    case "Find" :
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
                        ControleurAccueil.startAffichage("procpect");

                        dispose();
                    default:
                        break;
                }
            }
        });
    }

    private void createUIComponents() {
        panel1 = new JPanel();
        clientBtnCombo = new JComboBox<>();
        prospectBtnCombo = new JComboBox<>();
    }
}
