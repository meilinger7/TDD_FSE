package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertThrows(IllegalArgumentException.class, () -> {
            kinoVerwaltungOriginal.einplanenVorstellung(vorstellungOriginal);
        });
    }



}
