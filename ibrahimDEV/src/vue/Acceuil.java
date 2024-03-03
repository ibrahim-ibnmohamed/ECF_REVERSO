package vue;

import controleur.ControleurFormulaire;
import exception.MyException;
import model.entite.Client;
import model.entite.Prospect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static model.dao.DaoClient.create;
import static model.dao.DaoClient.findByName;

public class Acceuil extends JDialog {
    private ControleurFormulaire controleurFormulaire;
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
                        ControleurFormulaire controleurFormulaire= new ControleurFormulaire(client);
                        controleurFormulaire.createClient();
                        dispose();
                        break;
                    case "Update":
                        try {
                            ControleurFormulaire controleurFormulaire0= new ControleurFormulaire(client);
                            controleurFormulaire0.selectClientToUpdate();
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                        break;
                    case "Delete":
                        ControleurFormulaire controleurFormulaireDelete= new ControleurFormulaire(client);
                        controleurFormulaireDelete.selectClientToDelete();
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        break;
                    case "Find" :
                        ControleurFormulaire controleurFormulaireFind= new ControleurFormulaire(client);
                        controleurFormulaireFind.afficherTousLesClients();
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
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
                        ControleurFormulaire controleurFormulaire1 =new ControleurFormulaire(prospect);
                        dispose();
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Create");
                        break;
                    case "Update":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                        try {
                            ControleurFormulaire controleurFormulaire2 =new ControleurFormulaire(prospect);
                            controleurFormulaire2.selectProspctToUpdate();
                            dispose();
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Delete":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        break;
                    case "Find" :
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");
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
