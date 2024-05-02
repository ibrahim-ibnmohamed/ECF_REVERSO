/*package testUnitaire;

import exception.MyException;
import model.entite.Prospect;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Prospect.
 * Elle teste les méthodes de la classe Prospect en utilisant des paramètres.
 *//*
public class ProspectTest extends Prospect {

    /**
     * Teste la méthode setProspectInteresse avec différentes valeurs.
     *
     * @param i La valeur du prospect intéressé à tester.
     *//*
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"yes"})
    void testProspectInteresse(String i) {
        assertThrows(MyException.class, () -> setProspectInteresse(i));
    }

    /**
     * Teste la méthode setProspectInteresse avec des valeurs valides.
     *
     * @param i La valeur du prospect intéressé valide à tester.
     *//*
    @ParameterizedTest
    @ValueSource(strings = {"Oui", "NON"})
    void testValideProspectInteresse(String i) {
        assertDoesNotThrow(() -> setProspectInteresse(i));
    }

    /**
     * Teste la méthode setDateDeProspection avec une valeur nulle.
     *
     * @param i La valeur de la date de prospection à tester.
     *//*
    @ParameterizedTest
    @NullSource
    void testDateDeProspection(LocalDate i) {
        assertThrows(MyException.class, () -> setDateDeProspection(i));
    }

    /**
     * Teste la méthode setDateDeProspection avec une date valide.
     *
     * @param dateString La chaîne représentant la date de prospection valide à tester.
     *//*
    @ParameterizedTest
    @CsvSource({
            "20/02/2002"
    })
    void testValideDateDeProspection(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Prospect prospect = new Prospect();
        assertDoesNotThrow(() -> prospect.setDateDeProspection(date));
    }
}
*/
