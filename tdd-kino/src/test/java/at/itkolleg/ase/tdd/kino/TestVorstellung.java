package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestVorstellung {

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
    }

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void testVorstellungSaal() {
        assertEquals(kinosaalOriginal, vorstellungOriginal.getSaal());
    }

    @Test
    void testVorstellungZeitfenster() {
        assertEquals(Zeitfenster.ABEND, vorstellungOriginal.getZeitfenster());
    }

    @Test
    void testVorstellungDatum() {
        assertEquals(localDateOriginal, vorstellungOriginal.getDatum());
    }

    @Test
    void testVorstellungFilm() {
        assertEquals("Hangover", vorstellungOriginal.getFilm());
    }

    @Test
    void testExpectedExceptionNichtAusreichendGeld() {
        assertThrows(IllegalArgumentException.class, () -> {
            vorstellungOriginal.kaufeTicket('A', 5, 1F);
        });
    }


    @Test
    void testExpectedExceptionPlatzExistiertNicht() {
        assertThrows(IllegalArgumentException.class, () -> {
            vorstellungOriginal.kaufeTicket('A', 100, 15F);
        });
    }

    @Test
    void testExpectedExceptionPlatzBelegt() {
        vorstellungOriginal.kaufeTicket('A', 5, 15F);
        assertThrows(IllegalStateException.class, () -> {
            vorstellungOriginal.kaufeTicket('A', 5, 15F);
        });
    }

    @Test
    void testVorstellungEquals(){
        assertTrue(vorstellungOriginal.equals(vorstellungOriginal));
        assertFalse(vorstellungOriginal.equals(new Object()));
    }

}
