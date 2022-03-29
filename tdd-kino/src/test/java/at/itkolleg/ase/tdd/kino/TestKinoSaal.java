package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKinoSaal {

    private KinoSaal kinosaalOriginal;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("KS2", map);
    }

    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void testKinosaalName() {
        assertEquals("KS2", kinosaalOriginal.getName());
    }

    @Test
    void testKinosaalPlätze() {
        assertTrue(kinosaalOriginal.pruefePlatz('B', 5));
        assertFalse(kinosaalOriginal.pruefePlatz('C', 0));
        assertFalse(kinosaalOriginal.pruefePlatz('A', 11));
        assertFalse(kinosaalOriginal.pruefePlatz('X', 5));
    }

    @Test
    void testKinosaalEquals(){
        assertTrue(kinosaalOriginal.equals(kinosaalOriginal));
        assertFalse(kinosaalOriginal.equals(new Object()));
    }
}
