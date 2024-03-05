package vue;


import controleur.ControleurAccueil;
import controleur.ControleurFormulaire;
import exception.ControleurExcpetion;
import exception.MyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

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
    private JTextField tfDateDeProspection;
    private JTextField tfProspectInteresse;
    private JButton btnValider;
    private JButton btnAccueil;
    private JComboBox<String> clientComboBox;


    public Formulair(String choix) {
        setTitle("Formulaire " + " " + choix + "s");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(14, 2, 10, 10));
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);



        btnAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acceuil acceuil = new Acceuil();
                acceuil.setVisible(true);
                dispose();
            }
        });
        switch (choix) {

            case "createClient": {
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

                btnValider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        if (tfRaisonSociale.getText().isEmpty() ||
                                tfNomRue.getText().isEmpty() ||
                                tfNumRue.getText().isEmpty() ||
                                tfCodePostal.getText().isEmpty() ||
                                tfTelephone.getText().isEmpty() ||
                                tfVille.getText().isEmpty() ||
                                tfEmail.getText().isEmpty() ||
                                tfChiffreAffaire.getText().isEmpty() ||
                                tfNombreEmployes.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");

                        } else {
                            try {

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
                                JOptionPane.showMessageDialog(null,"Client créé avec succès !");

                               ControleurFormulaire.startAccueil();
                                dispose();

                            } catch (MyException ex) {
                                JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "vérifier la longueur ", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            catch (NumberFormatException Nf){
                                    JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "Veuillez vérifier les types de données qui existent dans les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            catch (NullPointerException NE){
                                JOptionPane.showMessageDialog(null, "Annulé: " );

                            };


                        }
                    }
                });
                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ControleurFormulaire.startAccueil();
                        dispose();

                    }
                });

            }

           break;

            case "updateClient": {
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


                if (ControleurFormulaire.clientVise==null) {
                    JOptionPane.showMessageDialog(null, "Annulé: ");
                    ControleurFormulaire.startAccueil();
                    dispose();

                }else {


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


                            try {
                                ControleurFormulaire.updateClient(id, raisonSociale, numDeRue, nomDeRue, codePostale, telephone, ville, email, commentaire, chiffreAffaire, nombreEmployes);
                            } catch (MyException ex) {
                                throw new RuntimeException(ex);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            JOptionPane.showMessageDialog(null, "Client mis à jour avec succès !");

                            ControleurFormulaire.startAccueil();
                            dispose();
                        }
                        });
                }


                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ControleurFormulaire.startAccueil();
                        dispose();


                    }
                });
            }
            break;
            case "deleteClient": {
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
                try {
                    ControleurFormulaire.choixClient();

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
                    @Override public void actionPerformed(ActionEvent e) {
                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce Client ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                        if (confirmDelete == JOptionPane.YES_OPTION) {
                            try {
                                ControleurFormulaire.deleteClient(ControleurFormulaire.clientVise);
                            } catch (MyException ex) {
                                throw new RuntimeException(ex);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            JOptionPane.showMessageDialog(null, "Client supprimé avec succès !");
                            ControleurFormulaire.startAccueil();
                            dispose();
                        }
                    }

                });
                }catch (ControleurExcpetion ce){
                    JOptionPane.showMessageDialog(null, ce.getMessage());

                } catch (MyException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       ControleurFormulaire.startAccueil();
                        dispose();

                    }
                });
            }
            break;

            case "createProsecte": {


                // Ajout des composants au formulaire
                lbId = new JLabel("ID Prospect:");
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

                add(new JLabel("DateDeProspection:"));
                tfDateDeProspection = new JTextField(); // Initialisation du champ de texte pour la date de prospection
                add(tfDateDeProspection);

                add(new JLabel("ProspectInteresse:"));
                tfProspectInteresse = new JTextField(); // Initialisation du champ de texte pour l'intérêt du prospect
                add(tfProspectInteresse);

                add(new JLabel("Commentaire:"));
                add(tfCommentaire);

                btnValider = new JButton("Valider");
                add(btnValider);

                btnAccueil = new JButton("Accueil");
                add(btnAccueil);

                btnValider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (tfRaisonSociale.getText().isEmpty() ||
                                tfNomRue.getText().isEmpty() ||
                                tfNumRue.getText().isEmpty() ||
                                tfCodePostal.getText().isEmpty() ||
                                tfTelephone.getText().isEmpty() ||
                                tfVille.getText().isEmpty() ||
                                tfEmail.getText().isEmpty() ||
                                tfDateDeProspection.getText().isEmpty() ||
                                tfProspectInteresse.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");

                        } else {

                            try {
                            int id = Integer.parseInt(tfId.getText());
                            String raisonSociale = tfRaisonSociale.getText();
                            String nomDeRue = tfNomRue.getText();
                            String numDeRue = tfNumRue.getText();
                            String codePostale = tfCodePostal.getText();
                            String telephone = tfTelephone.getText();
                            String ville = tfVille.getText();
                            String email = tfEmail.getText();
                            String commentaire = tfCommentaire.getText();
                            String dateDeProspection = tfDateDeProspection.getText();
                            String prospectInteresse = tfProspectInteresse.getText();

                                ControleurFormulaire.createProspect(id, raisonSociale, numDeRue, nomDeRue, codePostale, telephone, ville, email, commentaire, dateDeProspection, prospectInteresse);
                                JOptionPane.showMessageDialog(null, "Prospect créé avec succès !");

                                ControleurFormulaire.startAccueil();
                                dispose();

                            } catch (MyException ex) {
                                JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);

                            }catch (DateTimeParseException De){
                                JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "Le date n'est pas correct, il doit respecter le format jj/mm/aaaa", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }catch (NumberFormatException Nf){
                                JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "Veuillez vérifier les types de données qui existent dans les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            catch (NullPointerException NE){
                                JOptionPane.showMessageDialog(null, "Annulé: " );

                            };

                        }

                    }
                });
                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ControleurFormulaire.startAccueil();
                        dispose();

                    }
                });
            }
            break;
            case "updateProspect": {

                lbId = new JLabel("ID Prospect:");
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

                add(new JLabel("DateDeProspection:"));
                tfDateDeProspection = new JTextField(); // Initialisation du champ de texte pour la date de prospection
                add(tfDateDeProspection);

                add(new JLabel("ProspectInteresse:"));
                tfProspectInteresse = new JTextField(); // Initialisation du champ de texte pour l'intérêt du prospect
                add(tfProspectInteresse);

                add(new JLabel("Commentaire:"));
                add(tfCommentaire);

                btnValider = new JButton("Valider");
                add(btnValider);

                btnAccueil = new JButton("Accueil");
                add(btnAccueil);


// Met à jour les champs du formulaire avec les informations du prospect
                tfId.setText(Integer.toString(ControleurFormulaire.prospectVise.getId()));
                tfId.setEnabled(false);
                tfRaisonSociale.setText(ControleurFormulaire.prospectVise.getRaisonSociale());
                tfNomRue.setText(ControleurFormulaire.prospectVise.getNomDeRue());
                tfNumRue.setText(ControleurFormulaire.prospectVise.getNumeroDeRue());
                tfCodePostal.setText(ControleurFormulaire.prospectVise.getCodePostal());
                tfVille.setText(ControleurFormulaire.prospectVise.getVille());
                tfTelephone.setText(ControleurFormulaire.prospectVise.getTelephone());
                tfEmail.setText(ControleurFormulaire.prospectVise.getemail());
                tfDateDeProspection.setText(ControleurFormulaire.prospectVise.getDateDeProspection().toString());
                tfProspectInteresse.setText(ControleurFormulaire.prospectVise.getProspectInteresse());
                tfCommentaire.setText(ControleurFormulaire.prospectVise.getCommentaire());

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
                        String dateDeProspection = tfDateDeProspection.getText();
                        String prospectInteresse = tfProspectInteresse.getText();

                        try {
                            ControleurFormulaire.updateProspect(id, raisonSociale, numDeRue, nomDeRue, codePostale,
                                    telephone, ville, email, commentaire, dateDeProspection, prospectInteresse);
                            JOptionPane.showMessageDialog(null,"Prospect mis à jour avec succès !");
                        } catch (MyException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);

                        }catch (DateTimeParseException De){
                            JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "Le date n'est pas correct, il doit respecter le format jj/mm/aaaa", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }catch (NumberFormatException Nf){
                            JOptionPane.showMessageDialog(null, "Erreur lors de la création : " + "Veuillez vérifier les types de données qui existent dans les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (NullPointerException NE){
                            JOptionPane.showMessageDialog(null, "Annulé: " );

                        };
                        ControleurFormulaire.startAccueil();
                        dispose();
                    }
                });

                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ControleurFormulaire.startAccueil();
                        dispose();

                    }
                });
            }
            break;
            case "deleteProspect": {
                // Ajout des composants au formulaire
                lbId = new JLabel("ID Prospect:");
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

                add(new JLabel("DateDeProspection:"));
                tfDateDeProspection = new JTextField(); // Initialisation du champ de texte pour la date de prospection
                add(tfDateDeProspection);

                add(new JLabel("ProspectInteresse:"));
                tfProspectInteresse = new JTextField(); // Initialisation du champ de texte pour l'intérêt du prospect
                add(tfProspectInteresse);

                add(new JLabel("Commentaire:"));
                add(tfCommentaire);

                btnValider = new JButton("Valider");
                add(btnValider);

                btnAccueil = new JButton("Accueil");
                add(btnAccueil);
                try{
                    ControleurFormulaire.choixProspect();
                } catch (MyException e) {
                    throw new RuntimeException(e);

                }


                // Met à jour les champs du formulaire avec les informations du client
                tfId.setText(Integer.toString(ControleurFormulaire.prospectVise.getId()));
                tfId.setEnabled(false);
                tfRaisonSociale.setText(ControleurFormulaire.prospectVise.getRaisonSociale());
                tfNomRue.setText(ControleurFormulaire.prospectVise.getNomDeRue());
                tfNumRue.setText(ControleurFormulaire.prospectVise.getNumeroDeRue());
                tfCodePostal.setText(ControleurFormulaire.prospectVise.getCodePostal());
                tfVille.setText(ControleurFormulaire.prospectVise.getVille());
                tfTelephone.setText(ControleurFormulaire.prospectVise.getTelephone());
                tfEmail.setText(ControleurFormulaire.prospectVise.getemail());
                tfDateDeProspection.setText(ControleurFormulaire.prospectVise.getDateDeProspection().toString());
                tfProspectInteresse.setText(ControleurFormulaire.prospectVise.getProspectInteresse());
                tfCommentaire.setText(ControleurFormulaire.prospectVise.getCommentaire());

                btnValider.setText("Supprimer");
                btnValider.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce Client ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                        if (confirmDelete == JOptionPane.YES_OPTION) {

                            try {
                            ControleurFormulaire.deleteProspect(ControleurFormulaire.prospectVise);


                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        catch (NullPointerException NE){
                            JOptionPane.showMessageDialog(null, "Annulé: " );
                        }
                        JOptionPane.showMessageDialog(null, "Prospect supprimé avec succès !");
                           ControleurFormulaire.startAccueil();
                            dispose();
                        }
                    }
                });
                btnAccueil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ControleurFormulaire.startAccueil();
                        dispose();

                    }
                });
            }
            break;
            default:
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
