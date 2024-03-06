package testUnitaire;

import exception.MyException;
import model.entite.Societe;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SocieteTest extends Societe {



    @ParameterizedTest
    @NullSource
    @EmptySource
    void testSetRaisonSociale(String i) {
        assertThrows(MyException.class,()->{setRaisonSociale(i);});

    }
    @ParameterizedTest
    @ValueSource(strings = {"ibraTech"})
    void testSetRaisonSocialeValide(String i) {
        assertDoesNotThrow(()->{setRaisonSociale(i);});
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void testSetNumeroDeRue(String i) {
        assertThrows(MyException.class,()->{setNumeroDeRue(i);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"55"})
    void testSetNumeroDeRueValide(String i) {
        assertDoesNotThrow(()->{setNumeroDeRue(i);});
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void testSetNomDeRue(String i) {
        assertThrows(MyException.class,()->{setNomDeRue(i);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"rue de la pixe"})
    void testSetNomDeRueValide(String i) {
        assertDoesNotThrow(()->{setNomDeRue(i);});
    }


    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"1236547"})
    void testSetCodePostal(String i) {
        assertThrows(MyException.class,()->{setCodePostal(i);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"12365"})
    void testSetCodePostaleValide(String i) {
        assertDoesNotThrow(()->{setCodePostal(i);});
    }


    @ParameterizedTest
    @NullSource
    @EmptySource

    void testSetVille(String i) {
        assertThrows(MyException.class,()->{setVille(i);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"Paris"})
    void testSetVilleValide(String i) {
        assertDoesNotThrow(()->{setVille(i);});
    }


    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"000000505"})
    void testSetTelephone(String i) {
        assertThrows(MyException.class,()->{setTelephone(i);});
    }
    @ParameterizedTest
    @ValueSource(strings = {"0000000000"})
    void testSetTelephoneValide(String i) {
        assertDoesNotThrow(()->{setTelephone(i);});
    }



    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"dfffff.fer"})
    void testSetemail(String i) {
        assertThrows(MyException.class,()->{setemail(i);});
    }


    @ParameterizedTest
    @ValueSource(strings = {"dfffff@fer"})
    void testSetemailValide(String i) {
        assertDoesNotThrow(()->{setemail(i);});
    }

}