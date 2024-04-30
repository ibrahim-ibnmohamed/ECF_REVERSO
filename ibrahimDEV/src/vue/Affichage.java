package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controleur.ControleurAffichage;
import exception.DaoException;
import model.entite.Client;
import model.entite.Prospect;
import utilitaires.MyLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.logging.Level;


/**
 * Cette classe représente une fenêtre d'affichage des clients ou des prospects.
 */
public class Affichage extends JDialog {
    private JLabel lbTitre;
    private JTable tAffichage;
    private JButton btnAccueill;
    private JScrollPane jsScrol;
    private JPanel panelAffichage;


    /**
     * Constructeur de la classe Affichage.
     * @param choix Le choix entre l'affichage des clients ou des prospects.
     */
    public Affichage(String choix) {

        setTitle("la liste des " + " " + choix + "s");

        setContentPane(panelAffichage);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        if (choix.equals("client")) {
            try {
                ArrayList<Client> liste = ControleurAffichage.findAllClient();

                Comparator<Client> comparateur = Comparator.comparing(
                        client -> client.getRaisonSociale().toUpperCase(),
                        Collator.getInstance()
                );

                liste.sort(comparateur);

                Object[][] data = new Object[liste.size()][10];
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
                    data[i][8] = client.getChiffreDaffaire();
                    data[i][9] = client.getNombreEmployer();
                }

                String[] columnNames = {"identifiant", "Raison Social", "Num Rue", "Nom Rue",
                        "Ville", "Code Postal", "num Tel", "Adresse mail", "Chiffre d'affaire",
                        "Nombre d'employé"};
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                tAffichage.setModel(model);
                jsScrol.setViewportView(tAffichage);
                jsScrol.setVisible(true);
                tAffichage.setVisible(true);

            }
            catch (DaoException de){
                JOptionPane.showMessageDialog(null,de.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "un problème survient lors de l'affichage des clients.");
                MyLogger.LOGGER.log(Level.SEVERE,e.getMessage());
                System.exit(1);
            }

        } else if (choix.equals("procpect")) {

            try {
                ArrayList<Prospect> liste = ControleurAffichage.findAllProspect();
                // Trier la liste après avoir converti les noms en majuscules
                Comparator<Prospect> comparateur = Comparator.comparing(
                        prospect -> prospect.getRaisonSociale().toUpperCase(),
                        Collator.getInstance()
                );

                liste.sort(comparateur);


                Object[][] data = new Object[liste.size()][10];
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
                    data[i][8] = prospect.getDateDeProspection();
                    data[i][9] = prospect.getProspectInteresse();
                }

                String[] columnNames = {"identifiant", "Raison Social", "Num Rue", "Nom Rue",
                        "Ville", "Code Postal", "num Tel", "Adresse mail", "DateDeProspection",
                        "ProspectInteresse"};
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                tAffichage.setModel(model);
                jsScrol.setViewportView(tAffichage);
                jsScrol.setVisible(true);
                tAffichage.setVisible(true);

            }
            catch (DaoException de){
                JOptionPane.showMessageDialog(null,de.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "un problème survient lors de l'affichage des Prospects.");
                MyLogger.LOGGER.log(Level.SEVERE,e.getMessage());
                System.exit(1);
            }


        }
        btnAccueill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControleurAffichage.startAccueil();
                dispose();
            }
        });
    }

}
