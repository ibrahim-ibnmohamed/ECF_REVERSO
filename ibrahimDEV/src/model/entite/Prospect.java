package model.entite;

import exception.MyException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

       this.dateDeProspection=dateDeProspection;
    }

    public String getProspectInteresse() {
        return prospectInteresse;
    }

    public void setProspectInteresse(String prospectInteresse) {
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
