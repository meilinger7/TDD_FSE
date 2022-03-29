package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * Dieses Beispiel stammt aus https://training.cherriz.de/cherriz-training/1.0.0/testen/junit5.html
 */
public class App 
{
    public static void main( String[] args )
    {
        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        KinoSaal ks = new KinoSaal("LadyX",map);

        //Platz prüfen
        System.out.println(ks.pruefePlatz('A',11));
        System.out.println(ks.pruefePlatz('A',10));
        System.out.println(ks.pruefePlatz('B',10));
        System.out.println(ks.pruefePlatz('C',14));

        //Vorstellung hinzufügen
        LocalDate localDate = LocalDate.now();
        Vorstellung vs1 = new Vorstellung(ks, Zeitfenster.ABEND, localDate, "Hangover", 12.50F);
        Vorstellung vs2 = new Vorstellung(ks, Zeitfenster.NACHMITTAG, localDate.plusDays(2), "Herr der Ringe", 15.50F);
        System.out.println(vs1.getZeitfenster());
        System.out.println(vs1.getZeitfenster());


        //Vorstellungen über die Kinoverwaltung einplanen
        List<Vorstellung> vorstellungen = new LinkedList<>();
        vorstellungen.add(vs1);
        vorstellungen.add(vs2);
        KinoVerwaltung kv = new KinoVerwaltung();
        kv.einplanenVorstellung(vs1);
        kv.einplanenVorstellung(vs2);

        //Tickets für Vorstellungen ausgeben über Kinoverwaltung
        Ticket ticket = kv.kaufeTicket(vs1, 'A', 5, 15F);
        System.out.println(ticket.getSaal());
        System.out.println(ticket.getZeitfenster());
        System.out.println(ticket.getDatum());






    }
}
