package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acceuil extends JDialog {
    private JPanel panel1;
    private JLabel lbTitre;

    private JComboBox<String> clientBtnCombo;
    private JComboBox<String> prospectBtnCombo;

    public Acceuil() {
        setTitle("Accueil");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        clientBtnCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'élément sélectionné
                String choixSelectionne = (String) clientBtnCombo.getSelectedItem();

                // Exécuter du code en fonction de l'élément sélectionné
                switch (choixSelectionne) {
                    case "Create":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Create");

                        Formulair formulair=new Formulair();

                        formulair.setVisible(true);

                        dispose();

                        break;
                    case "Update":


                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                        break;
                    case "Delete":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        break;
                    case "Find" :
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");

                    default:
                        // Faire quelque chose si nécessaire pour les choix non attendus
                        break;
                }
            }
        });

    }
    {
        prospectBtnCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'élément sélectionné
                String choixSelectionne = (String) prospectBtnCombo.getSelectedItem();

                // Exécuter du code en fonction de l'élément sélectionné
                switch (choixSelectionne) {
                    case "Create":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Create");
                        break;
                    case "Update":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Update");
                        break;
                    case "Delete":
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Delete");
                        break;
                    case "Find" :
                        JOptionPane.showMessageDialog(Acceuil.this, "Vous avez choisi Find");

                    default:
                        // Faire quelque chose si nécessaire pour les choix non attendus
                        break;
                }
            }
        });
    }

    private void createUIComponents() {
        // Créez vos composants personnalisés ici si nécessaire
        panel1 = new JPanel(); // Créez votre panel
        clientBtnCombo = new JComboBox<>();
        prospectBtnCombo = new JComboBox<>();
    }

    private void afficherMenuActions(String type, JComboBox<String> comboBox) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemAjouter = new JMenuItem("Ajouter");
        JMenuItem itemAfficher = new JMenuItem("Afficher");
        JMenuItem itemModifier = new JMenuItem("Modifier");
        JMenuItem itemSupprimer = new JMenuItem("Supprimer");

        itemAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Acceuil.this, "Action Ajouter pour " + type);
            }
        });

        itemAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Acceuil.this, "Action Afficher pour " + type);
            }
        });

        itemModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Acceuil.this, "Action Modifier pour " + type);
            }
        });

        itemSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Acceuil.this, "Action Supprimer pour " + type);
            }
        });

        menu.add(itemAjouter);
        menu.add(itemAfficher);
        menu.add(itemModifier);
        menu.add(itemSupprimer);

        menu.show(comboBox, 0, comboBox.getHeight());
    }

    public static void main(String[] args) {
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
    }
}
