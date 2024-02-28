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



    public double getChiffreDaffaire() {
        return chiffreDaffaire;
    }

    public void setChiffreDaffaire(double chiffreDaffaire) throws MyException {
        if (chiffreDaffaire<200){
            throw new MyException("Le chiffre d’affaires devra être renseigné et être supérieur à 200");

        }else   this.chiffreDaffaire = chiffreDaffaire;
    }

    public int getNombreEmployer() {
        return nombreEmployer;
    }

    public void setNombreEmployer(int nombreEmployer) throws MyException {
        if (nombreEmployer < 1){
            throw new MyException("Le nombre d’employés devra être renseigné et être strictement supérieur à zéro");

        }else
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
