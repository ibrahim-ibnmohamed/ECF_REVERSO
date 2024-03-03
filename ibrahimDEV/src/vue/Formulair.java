package vue;

import controleur.ControleurFormulaire;
import model.entite.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox<String> clientComboBox;

    private Client client;

    public Formulair(Client client) {
        this.client=client;
        setTitle("Formulaire Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2, 10, 10));

        // Initialisation des champs du formulaire
        tfId = new JTextField();
        tfRaisonSociale = new JTextField();
        tfNomRue = new JTextField();
        tfNumRue = new JTextField();
        tfCodePostal = new JTextField();
        tfVille = new JTextField();
        tfTelephone = new JTextField();
        tfEmail = new JTextField();
        tfChiffreAffaire = new JTextField();
        tfNombreEmployes = new JTextField();
        tfCommentaire = new JTextField();

        // Remplissage des champs si un client est passé en paramètre
        if (client != null) {
            tfId.setText(Integer.toString(client.getId()));
            tfRaisonSociale.setText(client.getRaisonSociale());
            tfNomRue.setText(client.getNomDeRue());
            tfNumRue.setText(client.getNumeroDeRue());
            tfCodePostal.setText(client.getCodePostal());
            tfVille.setText(client.getVille());
            tfTelephone.setText(client.getTelephone());
            tfEmail.setText(client.getemail());
            tfChiffreAffaire.setText(Double.toString(client.getChiffreDaffaire()));
            tfNombreEmployes.setText(Integer.toString(client.getNombreEmployer()));
            tfCommentaire.setText(client.getCommentaire());
        }

        // Ajout des composants au formulaire
        lbId = new JLabel("ID Client:");
        add(lbId);
        add(tfId);

        add(new JLabel("Raison Sociale:"));
        add(tfRaisonSociale);

        add(new JLabel("Nom Rue:"));
        add(tfNomRue);

        add(new JLabel("Numéro Rue:"));
        add(tfNumRue);

        add(new JLabel("Code Postal:"));
        add(tfCodePostal);

        add(new JLabel("Ville:"));
        add(tfVille);

        add(new JLabel("Téléphone:"));
        add(tfTelephone);

        add(new JLabel("Email:"));
        add(tfEmail);

        add(new JLabel("Chiffre d'affaires:"));
        add(tfChiffreAffaire);

        add(new JLabel("Nombre Employés:"));
        add(tfNombreEmployes);

        add(new JLabel("Commentaire:"));
        add(tfCommentaire);

        btnValider = new JButton("Valider");
        add(btnValider);

        btnAccueil = new JButton("Accueil");
        add(btnAccueil);



        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addValiderListener(ActionListener listener) {
        btnValider.addActionListener(listener);
    }

    public void addAccueilListener(ActionListener listener) {
        btnAccueil.addActionListener(listener);
    }

    public void updateClient(Client client) {
        // Met à jour les champs du formulaire avec les informations du client
        tfId.setText(Integer.toString(client.getId()));
        tfRaisonSociale.setText(client.getRaisonSociale());
        tfNomRue.setText(client.getNomDeRue());
        tfNumRue.setText(client.getNumeroDeRue());
        tfCodePostal.setText(client.getCodePostal());
        tfVille.setText(client.getVille());
        tfTelephone.setText(client.getTelephone());
        tfEmail.setText(client.getemail());
        tfChiffreAffaire.setText(Double.toString(client.getChiffreDaffaire()));
        tfNombreEmployes.setText(Integer.toString(client.getNombreEmployer()));
        tfCommentaire.setText(client.getCommentaire());
    }





    public JTextField getTfId() {
        return tfId;
    }

    public JTextField getTfRaisonSociale() {
        return tfRaisonSociale;
    }

    public JTextField getTfNomRue() {
        return tfNomRue;
    }

    public JTextField getTfNumRue() {
        return tfNumRue;
    }

    public JTextField getTfCodePostal() {
        return tfCodePostal;
    }

    public JTextField getTfVille() {
        return tfVille;
    }

    public JTextField getTfTelephone() {
        return tfTelephone;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JTextField getTfChiffreAffaire() {
        return tfChiffreAffaire;
    }

    public JTextField getTfNombreEmployes() {
        return tfNombreEmployes;
    }

    public JTextField getTfCommentaire() {
        return tfCommentaire;
    }
}
