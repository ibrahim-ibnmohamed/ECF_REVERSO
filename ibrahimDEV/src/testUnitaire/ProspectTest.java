package testUnitaire;

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

public class ProspectTest extends Prospect {



    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"yes"})
    void testProspectInteresse(String i) {
        assertThrows(MyException.class,()->{setProspectInteresse(i);});

    }
    @ParameterizedTest
    @ValueSource(strings = {"Oui","NON"})
    void testValideProspectInteresse(String i) {
        assertDoesNotThrow(()->setProspectInteresse(i));
    }

    @ParameterizedTest
    @NullSource
    void testDateDeProspection(LocalDate i) {
        assertThrows(MyException.class,()-> setDateDeProspection(i));
    }
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

