import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Clasa pentru gestionarea grupurilor de contacte în cadrul unei agende telefonice.
 */
public class GestionareGrupuri {
    private static Map<String, GrupContacte> grupuri = new HashMap<>();
    private static List<String> numeGrupuri = new ArrayList<>();

    /**
     * Adaugă un contact la un grup specific sau creează grupul dacă nu există încă.
     *
     * @param contact   Contactul de adăugat la grup.
     * @param numeGrup  Numele grupului în care se va adăuga contactul.
     */
    public void adaugaContactLaGrup(Contact contact, String numeGrup) {
        if (!grupuri.containsKey(numeGrup)) {
            grupuri.put(numeGrup, new GrupContacte());
            numeGrupuri.add(numeGrup);
        }
        grupuri.get(numeGrup).adaugaContact(contact);
    }
    /**
     * Afișează contactele dintr-un grup specific.
     *
     * @param numeGrup Numele grupului pentru care se afișează contactele.
     */
    public static void afiseazaContacteInGrup(String numeGrup) {
        if (grupuri.containsKey(numeGrup)) {
            System.out.println("Contactele din grupul '" + numeGrup + "':");
            for (Contact contact : grupuri.get(numeGrup).getContacte()) {
                System.out.println(contact);
            }
        } else {
            System.out.println("Grupul '" + numeGrup + "' nu există.");
        }
    }
    /**
     * Returnează lista de nume a tuturor grupurilor existente.
     *
     * @return Lista de nume a grupurilor.
     */
    public static List<String> getNumeGrupuri() {
        return numeGrupuri;
    }

    /**
     * Creează un grup nou și adaugă persoanele din contacte.txt în acel grup.
     *
     * @param numeGrupNou   Numele noului grup.
     * @param listaContacte Lista de contacte disponibile.
     */
    public void creeazaGrup(String numeGrupNou, List<Contact> listaContacte) {
        if (grupuri.containsKey(numeGrupNou)) {
            System.out.println("Grupul cu numele " + numeGrupNou + " există deja.");
            return;
        }

        GrupContacte grupNou = new GrupContacte();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceți numele contactelor (terminați cu 'stop'):");

        String numeContact;
        while (!(numeContact = scanner.nextLine()).equalsIgnoreCase("stop")) {
            for (Contact contact : listaContacte) {
                if (contact.getNume().equalsIgnoreCase(numeContact)) {
                    grupNou.adaugaContact(contact);
                }
            }
        }
        grupuri.put(numeGrupNou, grupNou);
        System.out.println("Grupul " + numeGrupNou + " a fost creat cu succes.");
    }
    /**
     * Salvează grupurile într-un fișier specificat.
     *
     * @param numeFisier Numele fișierului în care vor fi salvate grupurile.
     */
    public void salveazaGrupuriInFisier(String numeFisier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (String numeGrup : numeGrupuri) {
                writer.write(numeGrup);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Eroare la salvarea grupurilor în fișier: " + e.getMessage());
        }
    }

    public GestionareGrupuri() {
    }


}
