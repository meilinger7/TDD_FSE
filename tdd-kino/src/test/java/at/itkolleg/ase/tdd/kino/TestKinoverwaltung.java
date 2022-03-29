package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestKinoverwaltung {

    private KinoVerwaltung kinoVerwaltungOriginal;
    private Vorstellung vorstellungOriginal;
    private KinoSaal kinosaalOriginal;
    private LocalDate localDateOriginal;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("KS2", map);
        localDateOriginal = LocalDate.now();

        //Vorsstellung erstellen
        vorstellungOriginal = new Vorstellung(kinosaalOriginal, Zeitfenster.ABEND, localDateOriginal, "Hangover", 12.50F);


        //Kino Verwaltung erstellen
        kinoVerwaltungOriginal = new KinoVerwaltung();
        kinoVerwaltungOriginal.einplanenVorstellung(vorstellungOriginal);
    }

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void testExpectedExceptionVorstellungBereitsEingeplant() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            kinoVerwaltungOriginal.einplanenVorstellung(vorstellungOriginal);
        });
        assertEquals(exception.getMessage(), "Die Vorstellung ist bereits eingeplant");
    }

    @Test
    void testTestKinoverwaltungEinplanenVorstellung() {
        List<Vorstellung> vorstellungList = new LinkedList<>();
        vorstellungList.add(vorstellungOriginal);
        assertEquals(kinoVerwaltungOriginal.getVorstellungen(), vorstellungList);
    }

    @Test
    void testTestKinoverwaltungEinplanenMehrererVorstellungen() {
        Vorstellung zweiteVorstellung = new Vorstellung(kinosaalOriginal, Zeitfenster.NACHMITTAG, localDateOriginal.plusDays(2), "Herr der Ringe", 10.50F);
        kinoVerwaltungOriginal.einplanenVorstellung(zweiteVorstellung);

        List<Vorstellung> vorstellungList = new LinkedList<>();
        vorstellungList.add(vorstellungOriginal);
        vorstellungList.add(zweiteVorstellung);
        assertEquals(kinoVerwaltungOriginal.getVorstellungen(), vorstellungList);
    }

}
