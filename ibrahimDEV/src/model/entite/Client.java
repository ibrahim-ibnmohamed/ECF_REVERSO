/**
 * Ce package contient les classes représentant les entités métier de l'application.
 * Ces entités sont utilisées pour représenter les objets métier manipulés par le système.
 * Les classes dans ce package sont utilisées pour encapsuler les données et comportements associés
 * à chaque entité spécifique.
 */
package model.entite;

import exception.MyException;

/**
 * La classe Client représente une société cliente dans le système.
 * Un client est une entité avec des attributs spécifiques comme le chiffre d'affaires et le nombre d'employés.
 * Elle étend la classe Societe qui représente une société générique.
 */
public class Client extends Societe {
    private double chiffreDaffaire; // Le chiffre d'affaires du client
    private int nombreEmployer; // Le nombre d'employés du client

    /**
     * Constructeur de la classe Client.
     * @param id L'identifiant du client.
     * @param raisonSociale Le nom de la société.
     * @param numeroDeRue Le numéro de rue de l'adresse du client.
     * @param nomDeRue Le nom de la rue de l'adresse du client.
     * @param codePostal Le code postal de l'adresse du client.
     * @param telephone Le numéro de téléphone du client.
     * @param ville La ville de l'adresse du client.
     * @param email L'adresse e-mail du client.
     * @param commentaire Un commentaire sur le client.
     * @param chiffreDaffaire Le chiffre d'affaires du client.
     * @param nombreEmployer Le nombre d'employés du client.
     * @throws MyException Si une erreur survient lors de la validation des attributs.
     */
    public Client(int id, String raisonSociale, String numeroDeRue, String nomDeRue, String codePostal,
                  String telephone, String ville, String email, String commentaire,
                  double chiffreDaffaire, int nombreEmployer) throws MyException {
        super(id, raisonSociale, numeroDeRue, nomDeRue, codePostal, telephone, ville, email, commentaire);
        this.setChiffreDaffaire(chiffreDaffaire);
        this.setNombreEmployer(nombreEmployer);
    }

    /**
     * Constructeur par défaut de la classe Client.
     */
    public Client() {}

    /**
     * Obtient le chiffre d'affaires du client.
     * @return Le chiffre d'affaires du client.
     */
    public double getChiffreDaffaire() {
        return chiffreDaffaire;
    }

    /**
     * Définit le chiffre d'affaires du client.
     * @param chiffreDaffaire Le chiffre d'affaires du client.
     * @throws MyException Si le chiffre d'affaires n'est pas valide.
     */
    public void setChiffreDaffaire(Double chiffreDaffaire) throws MyException {
        if (chiffreDaffaire == null || chiffreDaffaire.equals("")) {
            throw new MyException("Le chiffre d’affaires doit être renseigné.");
        }
        if (chiffreDaffaire <= 200) {
            throw new MyException("Le chiffre d’affaires doit être supérieur à 200.");
        }
        this.chiffreDaffaire = chiffreDaffaire;
    }

    /**
     * Obtient le nombre d'employés du client.
     * @return Le nombre d'employés du client.
     */
    public int getNombreEmployer() {
        return nombreEmployer;
    }

    /**
     * Définit le nombre d'employés du client.
     * @param nombreEmployer Le nombre d'employés du client.
     * @throws MyException Si le nombre d'employés n'est pas valide.
     */
    public void setNombreEmployer(Integer nombreEmployer) throws MyException {
        if (nombreEmployer == null) {
            throw new MyException("Le nombre d’employés doit être renseigné.");
        }
        if (nombreEmployer < 1) {
            throw new MyException("Le nombre d’employés doit être strictement supérieur à zéro.");
        }
        this.nombreEmployer = nombreEmployer;
    }

    /**
     * Représentation textuelle de l'objet Client.
     * @return Une chaîne de caractères représentant le client.
     */
    @Override
    public String toString() {
        return super.toString() + "Client{" +
                "chiffreDaffaire=" + chiffreDaffaire +
                ", nombreEmployer=" + nombreEmployer +
                '}';
    }
}
