package controleur;

import model.dao.DaoClient;
import model.entite.Client;
import vue.Affichage;

import java.util.ArrayList;

public class ControleurAffichage {

    public static void init(String choix){
        Affichage affichage= new Affichage(choix);
        affichage.setVisible(true);


    }
    public static ArrayList<Client> findAllClient() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            clients = DaoClient.findAll();
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée
        }
        return clients;
    }
}