package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.dao.DaoClient;
import model.dao.DaoProspect;
import model.entite.Client;
import model.entite.Prospect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Affichage extends JDialog {
    private JLabel lbTitre;
    private JTable tAffichage;
    private JButton btnAccueill;
    private JScrollPane jsScrol;
    private JPanel panelAffichage;

    public Affichage(String choix) {

        setTitle("la liste des " + " " + choix + "s");

        setContentPane(panelAffichage);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        if (choix.equals("client")) {
            try {
                ArrayList<Client> liste = DaoClient.findAll();

                Object[][] data = new Object[liste.size()][11];
                for (int i = 0; i < liste.size(); i++) {
                    Client client = liste.get(i);
                    data[i][0] = client.getId();
                    data[i][1] = client.getRaisonSociale();
                    data[i][2] = client.getNumeroDeRue();
                    data[i][3] = client.getNomDeRue();
                    data[i][4] = client.getVille();
                    data[i][5] = client.getCodePostal();
                    data[i][6] = client.getTelephone();
                    data[i][7] = client.getemail();
                    data[i][8] = client.getCommentaire();
                    data[i][9] = client.getChiffreDaffaire();
                    data[i][10] = client.getNombreEmployer();
                }

                String[] columnNames = {"identifiant", "Raison Social", "Num Rue", "Nom Rue",
                        "Ville", "Code Postal", "num Tel", "Adresse mail", "Commentaire", "Chiffre d'affaire",
                        "Nombre d'employé"};
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                tAffichage.setModel(model);
                jsScrol.setViewportView(tAffichage);
                jsScrol.setVisible(true);
                tAffichage.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace(); // Gérer les exceptions de manière appropriée
            }

        } else if (choix.equals("procpect")) {

            try {
                ArrayList<Prospect> liste = DaoProspect.findAll();

                Object[][] data = new Object[liste.size()][11];
                for (int i = 0; i < liste.size(); i++) {
                    Prospect prospect = liste.get(i);
                    data[i][0] = prospect.getId();
                    data[i][1] = prospect.getRaisonSociale();
                    data[i][2] = prospect.getNumeroDeRue();
                    data[i][3] = prospect.getNomDeRue();
                    data[i][4] = prospect.getVille();
                    data[i][5] = prospect.getCodePostal();
                    data[i][6] = prospect.getTelephone();
                    data[i][7] = prospect.getemail();
                    data[i][8] = prospect.getCommentaire();
                    data[i][9] = prospect.getDateDeProspection();
                    data[i][10] = prospect.getProspectInteresse();
                }

                String[] columnNames = {"identifiant", "Raison Social", "Num Rue", "Nom Rue",
                        "Ville", "Code Postal", "num Tel", "Adresse mail", "Commentaire", "DateDeProspection",
                        "ProspectInteresse"};
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                tAffichage.setModel(model);
                jsScrol.setViewportView(tAffichage);
                jsScrol.setVisible(true);
                tAffichage.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace(); // Gérer les exceptions de manière appropriée


            }


        }
        btnAccueill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acceuil acceuil = new Acceuil();
                acceuil.setVisible(true);
                dispose();
            }
        });
    }

}