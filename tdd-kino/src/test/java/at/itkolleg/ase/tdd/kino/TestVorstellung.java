package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import static at.itkolleg.ase.tdd.kino.Zeitfenster.ABEND;
import static at.itkolleg.ase.tdd.kino.Zeitfenster.NACHMITTAG;
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
        vorstellungOriginal = new Vorstellung(kinosaalOriginal, ABEND, localDateOriginal, "Hangover", 12.50F);
    }

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Beinhaltet richtigen Konosaal")
    void testVorstellungSaal() {
        assertEquals(kinosaalOriginal, vorstellungOriginal.getSaal(), "Falscher Kinosaal");
    }

    @Test
    @DisplayName("Beinhaltet richtiges Zeitfenster")
    void testVorstellungZeitfenster() {
        assertEquals(ABEND, vorstellungOriginal.getZeitfenster(), "Falsches Zeitfenster");
    }

    @Test
    @DisplayName("Beinhaltet richtiges Datum")
    void testVorstellungDatum() {
        assertEquals(localDateOriginal, vorstellungOriginal.getDatum(), "Falsches Datum");
    }

    @Test
    @DisplayName("Beinhaltet richtigen Film")
    void testVorstellungFilm() {
        assertEquals("Hangover", vorstellungOriginal.getFilm(), "Falscher Film");
    }


    @Test
    void testVorstellungKaufeTicket() {
        Ticket ticket = vorstellungOriginal.kaufeTicket('A', 5, 15F);
        assertEquals(ticket.getClass(), Ticket.class);
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
    void testVorstellungEquals() {
        assertTrue(vorstellungOriginal.equals(vorstellungOriginal));
        assertFalse(vorstellungOriginal.equals(new Object()));
    }


    @ParameterizedTest
    @CsvSource({
            "A, 3, 25",
            "B, 1, 15",
            "C, 4, 14",
            "A, 5, 50",
            "A, 8, 30",
            "A, 2, 22"
             })
    void paramatertestKaufeTicket(char reihe, int platz, float geld) {
        Ticket ticket = new Ticket(kinosaalOriginal.getName(), ABEND, localDateOriginal, reihe, platz);
        Ticket vorstellungTicket = vorstellungOriginal.kaufeTicket(reihe, platz, geld);

        assertAll("tickets",
                () -> assertEquals(ticket.getSaal(), vorstellungTicket.getSaal(), "falscher Saal"),
                () -> assertEquals(ticket.getZeitfenster(), vorstellungTicket.getZeitfenster(), "falsches Zeitfenster"),
                () -> assertEquals(ticket.getDatum(), vorstellungTicket.getDatum(), "falsches Datum"),
                () -> assertEquals(ticket.getReihe(), vorstellungTicket.getReihe(), "falsche Reuhe"),
                () -> assertEquals(ticket.getPlatz(), vorstellungTicket.getPlatz(), "falscher Platz")
        );
    }


    @ParameterizedTest
    @CsvSource({
            "A, 3, 2",
            "x, 1, 15",
            "B, 4, 1",
            "R, 5, 50",
            "F, 8, 30",
            "C, 2, 2"
    })
    void testExpectedExceptionIllegalArgument(char reihe, int platz, float geld) {

        assertThrows(IllegalArgumentException.class, () -> {
            vorstellungOriginal.kaufeTicket(reihe, platz, geld);
        });

    }



}
