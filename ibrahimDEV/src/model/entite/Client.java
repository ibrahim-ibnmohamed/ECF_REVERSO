package model.entite;


import exception.MyException;

public class Client extends Societe {
    private double chiffreDaffaire;
    private int  nombreEmployer ;
    public Client(int id,
                  String raisonSociale,
                  String numeroDeRue,
                  String nomDeRue,
                  String codePostal,
                  String telephone,
                  String ville,
                  String email,
                  String commentaire,
                  double chiffreDaffaire,
                  int nombreEmployer
    ) throws MyException {
        super(id, raisonSociale, numeroDeRue, nomDeRue, codePostal, telephone, ville, email, commentaire);
        this.setChiffreDaffaire(chiffreDaffaire);
        this.setNombreEmployer(nombreEmployer);
    }
    public Client(){}


    public double getChiffreDaffaire() {
        return chiffreDaffaire;
    }

    public void setChiffreDaffaire(Double chiffreDaffaire) throws MyException {
        if (chiffreDaffaire == null || chiffreDaffaire.equals("")) {
            throw new MyException("Le chiffre d’affaires doit être renseigné.");
        }
        if (chiffreDaffaire <= 200) {
            throw new MyException("Le chiffre d’affaires doit être supérieur à 200.");
        }
        this.chiffreDaffaire = chiffreDaffaire;
    }

    public int getNombreEmployer() {
        return nombreEmployer;
    }

    public void setNombreEmployer(Integer nombreEmployer) throws MyException {
        if (nombreEmployer == null) {
            throw new MyException("Le nombre d’employés doit être renseigné.");
        }
        if (nombreEmployer < 1) {
            throw new MyException("Le nombre d’employés doit être strictement supérieur à zéro.");
        }
        this.nombreEmployer = nombreEmployer;
    }


    @Override
    public String toString() {
        return super.toString()+ "Client{" +
                "chiffreDaffaire=" + chiffreDaffaire +
                ", nombreEmployer=" + nombreEmployer +
                '}';
    }
}
