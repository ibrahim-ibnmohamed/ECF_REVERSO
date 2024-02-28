package application;

import exception.MyException;
import model.entite.Prospect;

import java.sql.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws MyException {
        Prospect prospect =new Prospect(1,
                "ibraTech",
                "5",
                "rue de la republique",
                "25541","0733232315",
                "nancy","Ibrahim@hhh.com",
                "",
                Date.valueOf("2005-10-20" ),
                Prospect.ProspectInteresse.OUI);

        System.out.println(prospect);


    }
}
