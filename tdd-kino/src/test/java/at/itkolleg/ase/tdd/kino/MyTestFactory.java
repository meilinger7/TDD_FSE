package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static at.itkolleg.ase.tdd.kino.Zeitfenster.ABEND;
import static at.itkolleg.ase.tdd.kino.Zeitfenster.NACHMITTAG;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class MyTestFactory {

    private KinoVerwaltung kinoVerwaltungOriginal;
    private Vorstellung ersteVorstellungOriginal;
    private Vorstellung zweiteVorstellungOriginal;
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
        ersteVorstellungOriginal = new Vorstellung(kinosaalOriginal, ABEND, localDateOriginal, "Hangover", 9.50F);
        zweiteVorstellungOriginal = new Vorstellung(kinosaalOriginal, NACHMITTAG, localDateOriginal.plusDays(1), "Herr der Ringe", 9.50F);

        kinoVerwaltungOriginal = new KinoVerwaltung();
        kinoVerwaltungOriginal.einplanenVorstellung(ersteVorstellungOriginal);
        kinoVerwaltungOriginal.einplanenVorstellung(zweiteVorstellungOriginal);
    }

    @Disabled
    @DisplayName("Massentest Ticketkauf mit Stream")
    @TestFactory
    public Stream<DynamicTest> kaufeTicketStream() {


        return new Random(999)
                .ints(0, 1000)
                .limit(10)
                .mapToObj(i -> {
                    Vorstellung vorstellung = kinoVerwaltungOriginal.getVorstellungen().get(i % 2);
                    char reihe = (char) ((i % 10) + 65);
                    int platz = i % 15;
                    int geld = i % 50;

                    return dynamicTest(vorstellung.getFilm() + ", " + reihe + platz + ", " + geld + "€",
                            () -> assertDoesNotThrow(() -> {
                                try {
                                    kinoVerwaltungOriginal.kaufeTicket(vorstellung, reihe, platz, geld);
                                } catch (IllegalArgumentException e) {
                                    boolean errGeld = "Nicht ausreichend Geld.".equals(e.getMessage());
                                    boolean errPlatz = e.getMessage().contains("existiert nicht");
                                    assertTrue(errGeld || errPlatz);
                                } catch (IllegalStateException e) {
                                    assertTrue(e.getMessage().contains("ist bereits belegt."));
                                }
                            }));
                });

    }

    @DisplayName("Massentest Ticketkauf mit Collection")
    @TestFactory
    public Collection<DynamicTest> kaufeTicketCollection() {

        List<DynamicTest> testListe = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Vorstellung vorstellung = kinoVerwaltungOriginal.getVorstellungen().get(i % 2);
            char reihe = (char) ((i % 10) + 65);
            int platz = i % 15;
            int geld = i % 50;

            testListe.add(dynamicTest(vorstellung.getFilm() + ", " + reihe + platz + ", " + geld + "€",
                    () -> assertDoesNotThrow(() -> {
                        try {
                            kinoVerwaltungOriginal.kaufeTicket(vorstellung, reihe, platz, geld);
                        } catch (IllegalArgumentException e) {
                            boolean errGeld = "Nicht ausreichend Geld.".equals(e.getMessage());
                            boolean errPlatz = e.getMessage().contains("existiert nicht");
                            assertTrue(errGeld || errPlatz);
                        } catch (IllegalStateException e) {
                            assertTrue(e.getMessage().contains("ist bereits belegt."));
                        }
                    })));
        }

        return testListe;
    }




}
