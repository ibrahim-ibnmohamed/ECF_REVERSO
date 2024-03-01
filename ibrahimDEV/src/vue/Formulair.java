package vue;

import exception.MyException;
import model.dao.DaoClient;
import model.entite.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Formulair extends JDialog {
    private JTextField tfId;
    private JTextField tfCommentaire;
    private JTextField tfNombreEmployes;
    private JTextField tfRaisonSociale;
    private JTextField tfNomRue;
    private JTextField tfTelephone;
    private JTextField tfCodePostal;
    private JTextField tfEmail;
    private JTextField tfChiffreAffaire;
    private JTextField tfVille;
    private JTextField tfNumRue;
    private JPanel panelForm;
    private JLabel lbId;
    private JButton btnValider;
    private JButton btnAccueil;

    public Formulair() {
        setTitle("Création Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2, 10, 10));

        lbId = new JLabel("idClient:");
        add(lbId);
        tfId = new JTextField();
        add(tfId);

        add(new JLabel("Raison Sociale:"));
        tfRaisonSociale = new JTextField();
        add(tfRaisonSociale);

        add(new JLabel("Nom Rue:"));
        tfNomRue = new JTextField();
        add(tfNomRue);

        add(new JLabel("Numéro Rue:"));
        tfNumRue = new JTextField();
        add(tfNumRue);

        add(new JLabel("Code Postal:"));
        tfCodePostal = new JTextField();
        add(tfCodePostal);

        add(new JLabel("Ville:"));
        tfVille = new JTextField();
        add(tfVille);

        add(new JLabel("Téléphone:"));
        tfTelephone = new JTextField();
        add(tfTelephone);

        add(new JLabel("Email:"));
        tfEmail = new JTextField();
        add(tfEmail);

        add(new JLabel("Chiffre d'affaires:"));
        tfChiffreAffaire = new JTextField();
        add(tfChiffreAffaire);

        add(new JLabel("Nombre Employés:"));
        tfNombreEmployes = new JTextField();
        add(tfNombreEmployes);

        tfCommentaire = new JTextField(); // Ajout du champ commentaire
        add(new JLabel("Commentaire:"));
        add(tfCommentaire);

        btnValider = new JButton("Valider"); // Bouton pour valider
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idClient = tfId.getText();
                String raisonSociale = tfRaisonSociale.getText();
                String nomRue = tfNomRue.getText();
                String numRue = tfNumRue.getText();
                String codePostal = tfCodePostal.getText();
                String ville = tfVille.getText();
                String telephone = tfTelephone.getText();
                String email = tfEmail.getText();
                String chiffreAffaireStr = tfChiffreAffaire.getText();
                String nombreEmployesStr = tfNombreEmployes.getText();
                String commentaire = tfCommentaire.getText();

                // Vérification de la saisie
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

                    // Créer un Client
                    Client client = new Client(id, raisonSociale, numRue, nomRue, codePostal, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
                    // Appeler la méthode de DAO pour créer le client dans la base de données
                    DaoClient.create(client);
                    JOptionPane.showMessageDialog(Formulair.this, "Client créé avec succès !");
                } catch (NumberFormatException ex) {
                    // Gérer les erreurs de format des nombres
                    JOptionPane.showMessageDialog(Formulair.this, "Erreur de format pour le chiffre d'affaires ou le nombre d'employés", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException | IOException | MyException ex) {
                    // Gérer les erreurs lors de la création dans la base de données
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Formulair.this, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(btnValider);

        btnAccueil = new JButton("Accueil"); // Bouton pour retourner à l'accueil
        btnAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acceuil acceuil = new Acceuil();

                acceuil.setVisible(true);

                dispose();
            }
        });
        add(btnAccueil);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
