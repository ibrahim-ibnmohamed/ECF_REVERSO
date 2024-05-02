/*package testUnitaire;

import exception.MyException;
import entite.Societe;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Societe.
 * Elle teste les méthodes de la classe Societe en utilisant des paramètres.
 */
/*
class SocieteTest extends Societe {
    /**
     * Teste la méthode setRaisonSociale avec des valeurs nulles ou vides.
     *
     * @param i La valeur de la raison sociale à tester.
     *//*
    @ParameterizedTest
    @NullSource
    @EmptySource
    void testSetRaisonSociale(String i) {
        assertThrows(MyException.class, () -> setRaisonSociale(i));
    }
    /**
     * Teste la méthode setRaisonSociale avec une valeur valide.
     *
     * @param i La valeur de la raison sociale valide à tester.
     *//*
    @ParameterizedTest
    @ValueSource(strings = {"ibraTech"})
    void testSetRaisonSocialeValide(String i) {
        assertDoesNotThrow(() -> setRaisonSociale(i));
    }
    // Méthodes de test similaires pour les autres paramètres de la classe Societe...
}
*/