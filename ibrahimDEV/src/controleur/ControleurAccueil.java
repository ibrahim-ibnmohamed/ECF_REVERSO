package controleur;

import vue.Acceuil;

import java.util.ArrayList;

public class ControleurAccueil {

    private static Acceuil acceuil;
    public static void init(){
        acceuil= new Acceuil();
       acceuil.setVisible(true);

    }
}
