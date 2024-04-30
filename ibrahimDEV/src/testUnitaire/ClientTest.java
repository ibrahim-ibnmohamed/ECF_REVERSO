package testUnitaire;

import exception.MyException;
import model.entite.Client;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Client.
 * Elle teste les méthodes de la classe Client en utilisant des paramètres.
 */
public class ClientTest extends Client {

    /**
     * Teste la méthode setChiffreDaffaire avec différentes valeurs.
     *
     * @param i La valeur du chiffre d'affaires à tester.
     */
    @ParameterizedTest
    @ValueSource(doubles = {-200, 200.0})
    void testSetChiffreDaffaire(Double i) {
        assertThrows(MyException.class, () -> setChiffreDaffaire(i));
    }

    /**
     * Teste la méthode setChiffreDaffaire avec des valeurs valides.
     *
     * @param i La valeur du chiffre d'affaires valide à tester.
     */
    @ParameterizedTest
    @ValueSource(doubles = {1000.0, 200.1, 300.0})
    void testChiffreDaffaireValide(double i) {
        assertDoesNotThrow(() -> setChiffreDaffaire(i));
    }

    /**
     * Teste la méthode setNombreEmployer avec des valeurs nulles ou égales à zéro.
     *
     * @param i La valeur du nombre d'employés à tester.
     */
    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {0})
    void testNombreEmployer(Integer i) {
        assertThrows(MyException.class, () -> setNombreEmployer(i));
    }

    /**
     * Teste la méthode setNombreEmployer avec des valeurs valides.
     *
     * @param i La valeur du nombre d'employés valide à tester.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testNombreEmployerValide(int i) {
        assertDoesNotThrow(() -> setNombreEmployer(i));
    }
}
