import java.util.*;

public class Elettore {
  
  //@public invariant (data.isMaggiorenne()) ==> (vote == true);

  // ATTRIBUTI
  public final String nome, cognome, nazione, comune;
  public final char sex;
  public final Data data;
  public boolean vote;
  char[] codFisc;

  //COSTRUTTORI

  // Per i requires dell'attributo data leggere classe Data
  //@ requires (nazione.equals("IT")) ==> !comune.equals("");  
  //@ requires nome != null && cognome != null;
  //@ requires sex=='M' || sex=='F';
  //@ requires (\forall int i; i>= 12 && i <= 14; codFisc.charAt(i)>'0' && codFisc.charAt(i)<=9);
  //@ requires ( codFisc.charAt(11)>'A' && codFisc.charAt(11)<'Z') && (codFisc.charAt(15)>'A'  && codFisc.charAt(15)<'Z');
  //@ requires codFisc.charAt(11)>'a' && codFisc.charAt(11)<'z' && codFisc.charAt(15)>'a'  && codFisc.charAt(15)<'z';
  //@ requires !(nazione.equals("IT")) ==> (codFisc.charAt(11)=='Z' || codFisc.charAt(11)=='z');
  //@ requires nazione.equals("IT") ==> (!(codFisc.charAt(11)=='Z') && !(codFisc.charAt(11)=='z'));
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
    this.vote = false;

    if (this.nazione.equals("IT"))
      this.comune = comuneResidenza;
    else
      this.comune = "";

    if (!controllaSex(sex))
      throw new IllegalArgumentException("SESSO NON VALIDO");
    else
      this.sex = sex;

    if (!controllaCodFisc(codFisc))
      throw new IllegalArgumentException("CODICE FISCALE NON VALIDO");
    else
      this.codFisc = codFisc.toUpperCase().toCharArray();

  }

  private boolean controllaCodFisc(String codFisc) {
    if (!controlloCognome(codFisc.substring(0, 3)))
      return false;
    else if (!controlloNome(codFisc.substring(3, 6)))
      return false;
    else if (!controllaData(codFisc.substring(6, 11)))
      return false;
    else if (!cinquePos(codFisc))
      return false;
    return true;
  }

  private boolean controlloCognome(String treLettere) {
    int iControllo = 0;

    for (char c : this.cognome.toUpperCase().toCharArray()) {
      if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
        if (c != treLettere.charAt(iControllo)) {
          return false;
        } else {
          iControllo++;
          if (iControllo == 3)
            return true;
        }
      }
    }

    if (iControllo < 2) {
      for (char c : this.cognome.toUpperCase().toCharArray()) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
          if (c != treLettere.charAt(iControllo)) {
            return false;
          } else {
            iControllo++;
            if (iControllo == 3)
              return true;
          }
        }
      }
    }

    while (iControllo < 3) {
      if (treLettere.charAt(iControllo) != 'X')
        return false;
      iControllo++;
    }

    return true;
  }

  private boolean controlloNome(String treLettere) {
    int cons = 0, iControllo = 0;
    for (char c : this.nome.toUpperCase().toCharArray()) {
      if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
        cons++;
      }
    }

    if (cons < 4) {
      for (char c : this.nome.toUpperCase().toCharArray()) {
        if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
          if (c != treLettere.charAt(iControllo)) {
            return false;
          } else {
            iControllo++;
            if (iControllo == 3)
              return true;
          }
        }
      }

      if (iControllo < 2) {
        for (char c : this.nome.toUpperCase().toCharArray()) {
          if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            if (c != treLettere.charAt(iControllo)) {
              return false;
            } else {
              iControllo++;
              if (iControllo == 3)
                return true;
            }
          }
        }
      }

      while (iControllo < 3) {
        if (treLettere.charAt(iControllo) != 'X')
          return false;
        iControllo++;
      }
    } else {

      int count = 1;
      for (char c : this.nome.toUpperCase().toCharArray()) {
        if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
          if (c != treLettere.charAt(iControllo) && count != 2) {
            return false;
          } else if (count != 2) {
            iControllo++;
            if (iControllo == 3)
              return true;
          }
          count++;
        }
      }

      if (iControllo < 2) {
        for (char c : this.nome.toUpperCase().toCharArray()) {
          if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            if (c != treLettere.charAt(iControllo)) {
              return false;
            } else {
              iControllo++;
              if (iControllo == 3)
                return true;
            }
          }
        }
      }

      while (iControllo < 3) {
        if (treLettere.charAt(iControllo) != 'X')
          return false;
        iControllo++;
      }
    }
    return true;
  }

  private boolean controllaData(String str) {
    if (Integer.parseInt(str.substring(0, 2)) != this.data.anno % 100)
      return false;
    if (sex == 'F'){
      if (Integer.parseInt(str.substring(3, 5)) != (this.data.giorno % 100)+40)
        return false;
    } else {
      if (Integer.parseInt(str.substring(3, 5)) != this.data.giorno % 100)
        return false;
    }

    switch (str.charAt(2)) {
      case 'A': {if(this.data.mese != 1) return false; break;}
      case 'B': {if(this.data.mese != 2) return false; break;}
      case 'C': {if(this.data.mese != 3) return false; break;}
      case 'D': {if(this.data.mese != 4) return false; break;}
      case 'E': {if(this.data.mese != 5) return false; break;}
      case 'H': {if(this.data.mese != 6) return false; break;}
      case 'L': {if(this.data.mese != 7) return false; break;}
      case 'M': {if(this.data.mese != 8) return false; break;}
      case 'P': {if(this.data.mese != 9) return false; break;}
      case 'R': {if(this.data.mese != 10) return false; break;}
      case 'S': {if(this.data.mese != 11) return false; break;}
      case 'T': {if(this.data.mese != 12) return false; break;}
      default: return false; 
    }

    return true;
  }

  private boolean cinquePos(String codFisc){
    if (codFisc.length() != 16)
        return false;
      else if (codFisc.toUpperCase().charAt(15) > 'Z' || codFisc.toUpperCase().charAt(15) < 'A')
        return false;
      else if (codFisc.toUpperCase().charAt(11) > 'Z' || codFisc.toUpperCase().charAt(11) < 'A')
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
  
  //@ requires vote == false;
  //@ ensures vote == !\old(vote);
  public void esprimiVoto() {
    if (this.vote == true)
      throw new IllegalArgumentException("HAI GIA' VOTATO");
    this.vote = true;
  }

  public String toString() {
    return "Nome: " + this.nome + "\nCognome: " + this.cognome + "\nSesso: " + this.sex + "\n" + this.data
        + "\nCodice Fiscale: " + String.valueOf(this.codFisc) + "\nNazione: " + this.nazione + "\nResidenza: "
        + this.comune + "\nVoto: " + this.vote;
  }

}
