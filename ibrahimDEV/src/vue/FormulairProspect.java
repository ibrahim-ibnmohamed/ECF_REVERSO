
package vue;

        import controleur.ControleurFormulaire;
        import model.entite.Prospect;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class FormulairProspect extends JDialog {
    private JTextField tfId;
    private JTextField tfCommentaire;
    private JTextField tfProspectInteresse;
    private JTextField tfRaisonSociale;
    private JTextField tfNomRue;
    private JTextField tfTelephone;
    private JTextField tfCodePostal;
    private JTextField tfEmail;
    private JTextField tfDateDeProspection;
    private JTextField tfVille;
    private JTextField tfNumRue;
    private JPanel panelForm;
    private JLabel lbId;
    private JButton btnValider;
    private JButton btnAccueil;
    private JComboBox<String> procpectComboBox;

    public FormulairProspect(Prospect prospect) {
        setTitle("Formulaire Prospect");
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
        tfDateDeProspection = new JTextField();
        tfProspectInteresse = new JTextField();
        tfCommentaire = new JTextField();

        // Remplissage des champs si un procpect est passé en paramètre
        if (prospect != null) {
            tfId.setText(Integer.toString(prospect.getId()));
            tfRaisonSociale.setText(prospect.getRaisonSociale());
            tfNomRue.setText(prospect.getNomDeRue());
            tfNumRue.setText(prospect.getNumeroDeRue());
            tfCodePostal.setText(prospect.getCodePostal());
            tfVille.setText(prospect.getVille());
            tfTelephone.setText(prospect.getTelephone());
            tfEmail.setText(prospect.getemail());
            tfDateDeProspection.setText(prospect.getDateDeProspection().toString());
            tfProspectInteresse.setText(String.valueOf(prospect.getProspectInteresse()));
            tfCommentaire.setText(prospect.getCommentaire());
        }

        // Ajout des composants au formulaire
        lbId = new JLabel("ID Procpect:");
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

        add(new JLabel("Date :"));
        add(tfDateDeProspection);

        add(new JLabel("interessé:"));
        add(tfProspectInteresse);

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

    public void updateProspect(Prospect prospect) {
        // Met à jour les champs du formulaire avec les informations du prospect
        tfId.setText(Integer.toString(prospect.getId()));
        tfRaisonSociale.setText(prospect.getRaisonSociale());
        tfNomRue.setText(prospect.getNomDeRue());
        tfNumRue.setText(prospect.getNumeroDeRue());
        tfCodePostal.setText(prospect.getCodePostal());
        tfVille.setText(prospect.getVille());
        tfTelephone.setText(prospect.getTelephone());
        tfEmail.setText(prospect.getemail());
        tfDateDeProspection.setText(prospect.getDateDeProspection().toString());
        tfProspectInteresse.setText(String.valueOf(prospect.getProspectInteresse()));
        tfCommentaire.setText(prospect.getCommentaire());
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

    public JTextField getTfDateDeProspection() {
        return tfDateDeProspection;
    }

    public JTextField getTfProspectInteresse() {
        return tfProspectInteresse;
    }

    public JTextField getTfCommentaire() {
        return tfCommentaire;
    }


}
