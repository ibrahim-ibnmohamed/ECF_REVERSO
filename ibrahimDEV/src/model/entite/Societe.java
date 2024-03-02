package model.entite;


import exception.MyException;

abstract class Societe  {

    private int id;
    private  String raisonSociale;
    private  String numeroDeRue;
    private String nomDeRue;
    private String codePostal;
    private String ville;
    private  String telephone;
    private  String email;
    private  String commentaire;


    public Societe(int id,
                   String raisonSociale,
                   String numeroDeRue,
                   String nomDeRue,
                   String codePostal,
                   String telephone,
                   String ville,
                   String email,
                   String commentaire) throws MyException {
        this.setId(id);
        this.setRaisonSociale(raisonSociale);
        this.setNumeroDeRue(numeroDeRue);
        this.setNomDeRue(nomDeRue);
        this.setCodePostal(codePostal);
        this.setTelephone(telephone);
        this.setemail(email);
        this.setVille(ville);
        this.setCommentaire(commentaire);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNumeroDeRue() {
        return numeroDeRue;
    }

    public void setNumeroDeRue(String numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

    public String getNomDeRue() {
        return nomDeRue;
    }

    public void setNomDeRue(String nomDeRue) {
        this.nomDeRue = nomDeRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws MyException {
      if(telephone.length()<10){
            throw new MyException("le numero de téléphone est pas correct. ");
        }
        this.telephone = telephone;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("L'e-mail n'est pas valide.");
        }
        this.email = email;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Societe" +
                "id=" + id +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", numeroDeRue='" + numeroDeRue + '\'' +
                ", nomDeRue='" + nomDeRue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", commentaire='" + commentaire + '\'' ;
    }
}
