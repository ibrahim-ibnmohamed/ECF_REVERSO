package testUnitaire;

import exception.MyException;
import model.entite.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest extends Client {




    @ParameterizedTest
    @ValueSource(doubles = {-200, 200.0})
    void testSetChiffreDaffaire(Double i) {
        assertThrows(MyException.class, () -> setChiffreDaffaire(i));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1000.0, 200.1, 300.0})
    void testChiffreDaffaireValide(double i) {
        assertDoesNotThrow(() -> setChiffreDaffaire(i));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {0})
    void testNombreEmployer(Integer i) {
        assertThrows(MyException.class, () -> setNombreEmployer(i));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testNombreEmployerValide(int i) {
        assertDoesNotThrow(() -> setNombreEmployer(i));
    }
}
