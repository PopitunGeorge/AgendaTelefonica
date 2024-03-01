import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Clasa ManagerContacte se ocupă de gestionarea listei de contacte, inclusiv adăugarea, actualizarea, ștergerea și
 * interacțiunea cu fișierele.
 */
public class ManagerContacte {
    private List<Contact> listaContacte = new ArrayList<>();
    private Map<String, List<Contact>> grupuri = new HashMap<>();


    /**
     * Constructorul clasei ManagerContacte.
     * La crearea managerului, încarcăm datele din fișier (dacă există).
     */
    public ManagerContacte() {
        incarcaDinFisier("contacte.txt");
    }

    /**
     * Adaugă un contact nou în lista de contacte și salvează în fișier.
     *
     * @param contact Contactul de adăugat.
     */
    public void adaugaContact(Contact contact) {
        listaContacte.add(contact);
        salveazaInFisier("contacte.txt");
    }
    public Contact getContactByNume(String nume) {
        for (Contact contact : listaContacte) {
            if (contact.getNume().equalsIgnoreCase(nume)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Actualizează un contact existent în funcție de nume și salvează în fișier.
     *
     * @param nume         Numele contactului de actualizat.
     * @param contactNou   Contactul actualizat.
     */
    /*
    public void actualizeazaContact(String nume, Contact contactNou) {
        Iterator<Contact> iterator = listaContacte.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getNume().equals(nume)) {
                iterator.remove();
                break;
            }
        }
        listaContacte.add(contactNou);
        salveazaInFisier("contacte.txt");
    }
*/
    /**
     * Șterge un contact în funcție de nume și salvează în fișier.
     *
     * @param nume Numele contactului de șters.
     * @return
     */
    public boolean stergeContact(String nume) {
        Iterator<Contact> iterator = listaContacte.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getNume().equals(nume)) {
                iterator.remove();
                System.out.println("Contactul '" + nume + "' a fost șters.");
                salveazaInFisier("contacte.txt");
                return true;
            }
        }
        System.out.println("Contactul '" + nume + "' nu a fost găsit.");
        return false;
    }

    /**
     * Caută un contact în funcție de nume.
     *
     * @param numeCautat Numele contactului căutat.
     * @return
     */
    //public Contact cautaContact(String numeCautat) {
        //for (Contact contact : listaContacte) {
        //    if (contact.getNume().equalsIgnoreCase(numeCautat)) {
               // return contact;
          //  }
        ////}
        //return null; // Întoarce null dacă nu găsește contactul
   // }


    /**
     * Afișează lista de contacte.
     */
    public void afiseazaContacte() {
        System.out.println("Lista de contacte:");
        for (Contact contact : listaContacte) {
            System.out.println(contact);
        }
        System.out.println();
    }

    /**
     * Salvează lista de contacte într-un fișier CSV.
     *
     * @param numeFisier Numele fișierului în care se va salva lista de contacte.
     */
    public void salveazaInFisier(String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new File(numeFisier))) {
            for (Contact contact : listaContacte) {
                writer.println(contact.getNume() + "," + contact.getNumarTelefon() + "," + contact.getAdresaEmail());
            }
            System.out.println("Contactele au fost salvate în fișierul " + numeFisier);
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la salvarea în fișier: " + e.getMessage());
        }
    }

    /**
     * Încarcă lista de contacte dintr-un fișier CSV.
     *
     * @param numeFisier Numele fișierului din care se vor încărca contactele.
     */
    public void incarcaDinFisier(String numeFisier) {
        try (Scanner scanner = new Scanner(new File(numeFisier))) {
            listaContacte.clear();

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] elemente = linie.split(",");

                if (elemente.length == 3) {
                    String nume = elemente[0];
                    String numarTelefon = elemente[1];
                    String adresaEmail = elemente[2];

                    Contact contact = new Contact(nume, numarTelefon, adresaEmail);
                    listaContacte.add(contact);
                } else {
                    System.out.println("Linie invalidă în fișier: " + linie);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la citirea din fișier: " + e.getMessage());
        }
    }

    public List<Contact> getListaContacte() {
        return listaContacte;
    }

    public void setListaContacte(List<Contact> listaContacte) {
        this.listaContacte = listaContacte;
    }

    public List<String> getNumeGrupuri() {
    return null;
    }
    public boolean creeazaGrup(String numeGrup) {
        if (!grupuri.containsKey(numeGrup)) {
            grupuri.put(numeGrup, new ArrayList<>());
            return true;
        }
        return false;
    }

    public Contact cautaContact(String nume) {
        for (Contact contact : listaContacte) {
            if (contact.getNume().equals(nume)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Adaugă un contact existent la un grup specificat. Dacă grupul nu există, acesta va fi creat.
     *
     * @param numeGrup Numele grupului la care să se adauge contactul.
     * @param numeContact Numele contactului care trebuie adăugat la grup.
     * @return true dacă contactul a fost adăugat cu succes la grup, false dacă contactul nu există.
     */
    public boolean adaugaContactLaGrup(String numeGrup, String numeContact) {
        Contact contact = cautaContact(numeContact);
        if (contact != null) {
            grupuri.computeIfAbsent(numeGrup, k -> new ArrayList<>()).add(contact);
            return true;
        }
        return false;
    }
    public void actualizeazaContact(String numeActual, Contact contactNou) {
        for (Contact contact : listaContacte) {
            if (contact.getNume().equals(numeActual)) {
                contact.setNume(contactNou.getNume());
                contact.setNumarTelefon(contactNou.getNumarTelefon());
                contact.setAdresaEmail(contactNou.getAdresaEmail());
                return;
            }
        }

    }

    public boolean stergeContactDinGrup(String numeGrup, String numeContact) {
        List<Contact> contacte = grupuri.get(numeGrup);
        if (contacte != null) {
            for (Contact contact : contacte) {
                if (contact.getNume().equals(numeContact)) {
                    contacte.remove(contact);
                    return true;
                }
            }
        }
        return false;
    }
    public List<Contact> extrageContacteDinBazaDeDate() {
        List<Contact> contacte = new ArrayList<>();
        String sql = "SELECT nume, telefon, email FROM Contacte"; // Omiterea ID-ului
        try (Connection conn = DataBaseConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Presupunând că ai un constructor care nu necesită ID
                Contact contact = new Contact(
                        rs.getString("nume"),
                        rs.getString("telefon"),
                        rs.getString("email"));
                contacte.add(contact);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contacte;
    }
    public Contact cautaContactInBazaDeDate(String nume) {
        String sql = "SELECT * FROM Contacte WHERE nume = ?";
        try (Connection conn = DataBaseConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nume);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Contact(
                            rs.getString("nume"),
                            rs.getString("telefon"),
                            rs.getString("email"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // sau arunca o exceptie, in functie de cum preferi sa gestionezi cazul in care contactul nu este gasit
    }



}
