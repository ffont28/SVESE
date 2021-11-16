import java.time.LocalDate;
import java.util.Objects;


public class Data{
  public final int giorno, mese, anno;
  LocalDate lt = LocalDate.now();
  final int annoNow  = Integer.parseInt(lt.toString().split("-")[0]);
  final int meseNow  = Integer.parseInt(lt.toString().split("-")[1]);
  final int giornoNow  = Integer.parseInt(lt.toString().split("-")[2]);

  public Data(int giorno, int mese, int anno){
    Objects.requireNonNull(giorno);
    Objects.requireNonNull(mese);
    Objects.requireNonNull(anno);

    if (anno <= annoNow)
        this.anno = anno;
    else throw new IllegalArgumentException("L'anno non può essere maggiore di quello attuale");
    if ((mese > 0 && mese < 12) && ((anno != annoNow) || (mese <= meseNow)))
            this.mese=mese;
    else throw new IllegalArgumentException("Il mese non è corretto");
    
/**
    if (giorno <= 0 || giorno >= 32 || ((mese == 2 || mese == 4 || mese == 6 || mese == 9 || mese==11) && giorno >= 31) || (mese ==2 && !bisestile(anno) && giorno >= 29)
            || (mese ==2 && bisestile(anno) && giorno >= 30) || (anno == annoNow && mese == meseNow && giorno > giornoNow))
 */ 
    if (giorno > 0 && giorno < 32 && 
        (!(mese == 2 || mese == 4 || mese == 6 || mese == 9 || mese==11) || giorno <31) &&  
        (!(mese ==2 && !bisestile(anno)) || giorno < 29) && 
        (!(mese ==2 && bisestile(anno)) || giorno < 30) &&
        (!(anno == annoNow && mese == meseNow) || giorno <= giornoNow))
        this.giorno = giorno;
    else throw new IllegalArgumentException("Il giorno non è corretto");
  }

  private boolean bisestile(int anno){
      return (anno > 1584 && 
        ((anno % 400 == 0) || (anno %4 == 0 && anno % 100 != 0)));
  }

  public boolean isMaggiorenne(){
      return ((annoNow - this.anno > 18) || 
      ( (annoNow == this.anno) && (meseNow - this.mese >= 0) && (giornoNow - this.giorno >= 0) ) );
  }

  public String toString(){
    return "Data di nascita: " + this.giorno + "/" + this.mese + "/" + this.anno;
  }

  public static void main(String[] args) {
      Data d = new Data(29, 02, 2016);
      System.out.println(d);
  }
}
