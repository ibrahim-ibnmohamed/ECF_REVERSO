package model.entite;

import exception.MyException;

import java.time.LocalDate;

// Enumération pour "Prospect intéressé"


public class Prospect extends Societe  {
    private LocalDate dateDeProspection;
    private String prospectInteresse;


    public enum ProspectInteresse {
        OUI, NON
    }

    public Prospect(int id,
                    String raisonSociale,
                    String numeroDeRue,
                    String nomDeRue,
                    String codePostal,
                    String telephone,
                    String ville,
                    String email,
                    String commentaire,
                    LocalDate dateDeProspection,
                    String prospectInteresse) throws MyException {
        super(id, raisonSociale, numeroDeRue, nomDeRue, codePostal, telephone, ville, email, commentaire);
        this.setProspectInteresse(prospectInteresse);
        this.setDateDeProspection(dateDeProspection);
    }

    public LocalDate getDateDeProspection() {
        return dateDeProspection;
    }

    public void setDateDeProspection(LocalDate dateDeProspection) throws MyException {
        if (dateDeProspection == null|| dateDeProspection.equals("")){
            throw new MyException("Veuillez remplir le champ 'Date de Prospection'.");
        }

       this.dateDeProspection=dateDeProspection;
    }

    public String getProspectInteresse() {
        return prospectInteresse;
    }

    public void setProspectInteresse(String prospectInteresse) throws MyException {
        if (prospectInteresse == null|| prospectInteresse.equals("")){
            throw new MyException("Veuillez remplir le champ 'Interesse'.");
        }
        this.prospectInteresse = prospectInteresse;
    }

    @Override
    public String toString() {
        return super.toString()+"Prospect{" +
                "dateDeProspection=" + dateDeProspection +
                ", prospectInteresse='" + prospectInteresse + '\'' +
                '}';
    }
    // Vérifie le format de la date

}
