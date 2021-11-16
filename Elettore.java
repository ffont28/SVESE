import java.util.*;

public class Elettore {

    // ATTRIBUTI
    public final String nome, cognome, nazione, comune;
    public final char sex;
    private final Data data;
    private boolean vote;
    char[] codFisc;

    // COSTRUTTORI
    public Elettore(String nome, String cognome, Data data, char sex, String codFisc, String nazione,
            String comuneResidenza) {
        Objects.requireNonNull(nome, "NOME NON PUO ESSERE NULL");
        Objects.requireNonNull(cognome, "COGNOME NON PUO ESSERE NULL");
        Objects.requireNonNull(codFisc, "CODICE FISCALE NON PUO ESSERE NULL");
        Objects.requireNonNull(nazione, "NAZIONE NON PUO ESSERE NULL");
        Objects.requireNonNull(comuneResidenza, "COMUNE DI RESIDENZA NON PUO ESSERE NULL");
        this.nome = nome;
        this.cognome = cognome;
        this.nazione = nazione;
        this.data = data;
        this.comune = comuneResidenza;
        this.vote = false;

        if (!controllaCodFisc(codFisc))
            throw new IllegalArgumentException("CODICE FISCALE NON VALIDO");
        else
            this.codFisc = codFisc.toCharArray();
        if (!controllaSex(sex))
            throw new IllegalArgumentException("SESSO NON VALIDO");
        else
            this.sex = sex;
    }

    private boolean controllaCodFisc(String codFisc) {
        if (codFisc.length() != 16)
            return false;
        else if (codFisc.toUpperCase().charAt(15) > 'Z' && codFisc.toUpperCase().charAt(15) < 'A')
            return false;
        else if (codFisc.toUpperCase().charAt(11) > 'Z' && codFisc.toUpperCase().charAt(11) < 'A')
            return false;
        else if (!nazione.equals("IT") && codFisc.toUpperCase().charAt(11) != 'Z')
            return false;
        else if (nazione.equals("IT") && codFisc.toUpperCase().charAt(11) == 'Z')
            return false;
        for (int i = 12; i < 15; i++)
            if (codFisc.charAt(i) < '0' && codFisc.charAt(i) > '9')
                return false;

        return true;
    }

    private boolean controllaSex(char sex) {
        if (sex != 'M' && sex != 'F')
            return false;
        return true;
    }

    // METODI
    public void esprimiVoto() {
        vote = true;
    }

    public String toString() {
        return "Nome: " + this.nome + "\nCognome: " + this.cognome + "\nSesso: " + this.sex + "\n" + this.data
                + "\nCodice Fiscale: " + String.valueOf(this.codFisc) + "\nNazione: " + this.nazione + "\nResidenza: "
                + this.comune + "\nVoto: " + this.vote;
    }

}