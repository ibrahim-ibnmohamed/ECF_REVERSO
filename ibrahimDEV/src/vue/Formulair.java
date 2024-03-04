package vue;

import controleur.ControleurAccueil;
import controleur.ControleurFormulaire;
import exception.MyException;

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
    private JComboBox<String> clientComboBox;



    public Formulair(String choix) throws MyException {
        setTitle("Formulaire" + "" + choix + "s");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2, 10, 10));
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);




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



        btnAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acceuil acceuil = new Acceuil();
                acceuil.setVisible(true);
                dispose();
            }
        });



        if (choix.equals("createClient")) {


            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(tfId.getText());
                    String raisonSociale = tfRaisonSociale.getText();
                    String nomDeRue = tfNomRue.getText();
                    String numDeRue = tfNumRue.getText();
                    String codePostale = tfCodePostal.getText();
                    String telephone = tfTelephone.getText();
                    String ville = tfVille.getText();
                    String email = tfEmail.getText();
                    String commentaire = tfCommentaire.getText();
                    double chiffreAffaire = Double.parseDouble(tfChiffreAffaire.getText());
                    int nombreEmployes = Integer.parseInt(tfNombreEmployes.getText());


                    ControleurFormulaire.createClient(id, raisonSociale, numDeRue, nomDeRue, codePostale, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
                    ControleurAccueil.init();
                    dispose();

                }


            });
        }

        if (choix.equals("updateClient"))

        {
            ControleurFormulaire.selectClient();


            // Met à jour les champs du formulaire avec les informations du client
            tfId.setText(Integer.toString(ControleurFormulaire.clientVise.getId()));
            tfId.setEnabled(false);
            tfRaisonSociale.setText(ControleurFormulaire.clientVise.getRaisonSociale());
            tfNomRue.setText(ControleurFormulaire.clientVise.getNomDeRue());
            tfNumRue.setText(ControleurFormulaire.clientVise.getNumeroDeRue());
            tfCodePostal.setText(ControleurFormulaire.clientVise.getCodePostal());
            tfVille.setText(ControleurFormulaire.clientVise.getVille());
            tfTelephone.setText(ControleurFormulaire.clientVise.getTelephone());
            tfEmail.setText(ControleurFormulaire.clientVise.getemail());
            tfChiffreAffaire.setText(Double.toString(ControleurFormulaire.clientVise.getChiffreDaffaire()));
            tfNombreEmployes.setText(Integer.toString(ControleurFormulaire.clientVise.getNombreEmployer()));
            tfCommentaire.setText(ControleurFormulaire.clientVise.getCommentaire());

            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(tfId.getText());
                    String raisonSociale= tfRaisonSociale.getText();
                    String nomDeRue=tfNomRue.getText();
                    String numDeRue=tfNumRue.getText();
                    String codePostale=tfCodePostal.getText();
                    String telephone=tfTelephone.getText();
                    String ville= tfVille.getText();
                    String email=tfEmail.getText();
                    String commentaire=tfCommentaire.getText();
                    double chiffreAffaire= Double.parseDouble(tfChiffreAffaire.getText());
                    int  nombreEmployes=Integer.parseInt(tfNombreEmployes.getText());


                    try {
                        ControleurFormulaire.updateClient(id,raisonSociale,numDeRue,nomDeRue,codePostale,telephone,ville,email,commentaire,chiffreAffaire,nombreEmployes);
                    } catch (MyException ex) {
                        throw new RuntimeException(ex);
                    }
                    ControleurAccueil.init();
                    dispose();

                }


            });
        }
        if (choix.equals("deleteClient")) {
            ControleurFormulaire.selectClient();


            // Met à jour les champs du formulaire avec les informations du client
            tfId.setText(Integer.toString(ControleurFormulaire.clientVise.getId()));
            tfId.setEnabled(false);
            tfRaisonSociale.setText(ControleurFormulaire.clientVise.getRaisonSociale());
            tfNomRue.setText(ControleurFormulaire.clientVise.getNomDeRue());
            tfNumRue.setText(ControleurFormulaire.clientVise.getNumeroDeRue());
            tfCodePostal.setText(ControleurFormulaire.clientVise.getCodePostal());
            tfVille.setText(ControleurFormulaire.clientVise.getVille());
            tfTelephone.setText(ControleurFormulaire.clientVise.getTelephone());
            tfEmail.setText(ControleurFormulaire.clientVise.getemail());
            tfChiffreAffaire.setText(Double.toString(ControleurFormulaire.clientVise.getChiffreDaffaire()));
            tfNombreEmployes.setText(Integer.toString(ControleurFormulaire.clientVise.getNombreEmployer()));
            tfCommentaire.setText(ControleurFormulaire.clientVise.getCommentaire());

            btnValider.setText("Supprimer");
            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ControleurFormulaire.deleteClient(ControleurFormulaire.clientVise);
                    } catch (MyException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

        }




    }




            public void addValiderListener (ActionListener listener){
                btnValider.addActionListener(listener);

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
