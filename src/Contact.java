/**
 * Clasa Contact reprezintă o entitate care stochează informații despre un contact, cum ar fi numele,
 * numărul de telefon și adresa de email.
 */
public class Contact {
    private String nume;
    private String numarTelefon;
    private String adresaEmail;

    /**
     * Constructorul clasei Contact.
     *
     * @param nume          Numele contactului.
     * @param numarTelefon  Numărul de telefon al contactului.
     * @param adresaEmail   Adresa de email a contactului.
     */
    public Contact(String nume, String numarTelefon, String adresaEmail) {
        this.nume = nume;
        this.numarTelefon = numarTelefon;
        this.adresaEmail = adresaEmail;
    }

    /**
     * Returnează o reprezentare textuală a contactului.
     *
     * @return String care reprezintă contactul.
     */
    @Override
    public String toString() {
        return "Contact{" +
                "nume='" + nume + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                ", adresaEmail='" + adresaEmail + '\'' +
                '}';
    }

    /**
     * Returnează adresa de email a contactului.
     *
     * @return Adresa de email a contactului.
     */
    public String getAdresaEmail() {
        return adresaEmail;
    }
    /**
     * Setează adresa de email a contactului.
     *
     * @param adresaEmail Noua adresă de email a contactului.
     */
    public void setAdresaEmail(String adresaEmail) {
        this.adresaEmail = adresaEmail;
    }



    /**
     * Returnează numărul de telefon al contactului.
     *
     * @return Numărul de telefon al contactului.
     */
    public String getNumarTelefon() {
        return numarTelefon;
    }

    /**
     * Setează numărul de telefon al contactului.
     *
     * @param numarTelefon Noul număr de telefon al contactului.
     */
    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    /**
     * Returnează numele contactului.
     *
     * @return Numele contactului.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Setează numele contactului.
     *
     * @param nume Noul nume al contactului.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }
}
