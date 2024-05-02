package entite;

import exception.MyException;

import java.time.LocalDate;

/**
 * Représente un prospect, qui est une entreprise potentiellement intéressée par les services proposés.
 * Hérite de la classe Societe.
 */
public class Prospect extends Societe {
    private LocalDate dateDeProspection;
    private String prospectInteresse;

    /**
     * Constructeur pour initialiser un objet Prospect avec les informations spécifiées.
     *
     * @param id               L'identifiant du prospect.
     * @param raisonSociale    La raison sociale du prospect.
     * @param numeroDeRue      Le numéro de rue du prospect.
     * @param nomDeRue         Le nom de la rue du prospect.
     * @param codePostal       Le code postal du prospect.
     * @param telephone        Le numéro de téléphone du prospect.
     * @param ville            La ville du prospect.
     * @param email            L'adresse e-mail du prospect.
     * @param commentaire      Un commentaire sur le prospect.
     * @param dateDeProspection La date de prospection du prospect.
     * @param prospectInteresse L'intérêt du prospect pour les services proposés (oui/non).
     * @throws MyException Si une erreur survient lors de la validation des données.
     */
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

    /**
     * Constructeur par défaut.
     */
    public Prospect() {
    }

    /**
     * Obtient la date de prospection du prospect.
     *
     * @return La date de prospection du prospect.
     */
    public LocalDate getDateDeProspection() {
        return dateDeProspection;
    }

    /**
     * Définit la date de prospection du prospect.
     *
     * @param dateDeProspection La date de prospection à définir.
     * @throws MyException Si la date de prospection n'est pas valide.
     */
    public void setDateDeProspection(LocalDate dateDeProspection) throws MyException {
        if (dateDeProspection == null) {
            throw new MyException("Veuillez remplir le champ 'Date de Prospection'.");
        }
        this.dateDeProspection = dateDeProspection;
    }

    /**
     * Obtient l'intérêt du prospect pour les services proposés.
     *
     * @return L'intérêt du prospect pour les services proposés (oui/non).
     */
    public String getProspectInteresse() {
        return prospectInteresse;
    }

    /**
     * Définit l'intérêt du prospect pour les services proposés.
     *
     * @param prospectInteresse L'intérêt du prospect pour les services proposés (oui/non).
     * @throws MyException Si l'intérêt du prospect n'est pas valide.
     */
    public void setProspectInteresse(String prospectInteresse) throws MyException {
        if (prospectInteresse == null || prospectInteresse.isEmpty()) {
            throw new MyException("Veuillez remplir le champ 'Interesse'.");
        }
        // Vérifier si la valeur est "oui" ou "non" (en ignorant la casse)
        if (!prospectInteresse.equalsIgnoreCase("oui") && !prospectInteresse.equalsIgnoreCase("non")) {
            throw new MyException("La valeur de 'Interesse' doit être 'oui' ou 'non'.");
        }
        this.prospectInteresse = prospectInteresse;
    }

    /**
     * Représentation textuelle de l'objet Prospect.
     *
     * @return Une chaîne de caractères représentant l'objet Prospect.
     */
    @Override
    public String toString() {
        return super.toString() + "Prospect{" +
                "dateDeProspection=" + dateDeProspection +
                ", prospectInteresse='" + prospectInteresse + '\'' +
                '}';
    }
}
