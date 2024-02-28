package model.entite;

import exception.MyException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Enumération pour "Prospect intéressé"


public class Prospect extends Societe  {
    private Date dateDeProspection;
    private ProspectInteresse prospectInteresse;


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
                    Date dateDeProspection,
                    ProspectInteresse prospectInteresse) throws MyException {
        super(id, raisonSociale, numeroDeRue, nomDeRue, codePostal, telephone, ville, email, commentaire);
        this.setProspectInteresse(prospectInteresse);
        this.setDateDeProspection(dateDeProspection);
    }

    public Date getDateDeProspection() {
        return dateDeProspection;
    }

    public void setDateDeProspection(Date dateDeProspection) throws MyException {

        if(isValidDate(dateDeProspection.toString()))this.dateDeProspection = dateDeProspection;
       else  {throw new MyException("Le format de date n'est pas correct.");

        }
    }

    public ProspectInteresse getProspectInteresse() {
        return prospectInteresse;
    }

    public void setProspectInteresse(ProspectInteresse prospectInteresse) {
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
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
