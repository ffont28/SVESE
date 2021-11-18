import java.util.*;

public class Main {
    public static void main(String[] args) {
        Elettore elettore = new Elettore("Lorenzo", "Filipponi", new Data(11, 8, 2000), 'M', "FLPLNZ00M11F205W", "IT",
                "Corsico");
        System.out.println(elettore);
        Elettore elettore2 = new Elettore("Francesco", "Fontana", new Data(28, 5, 1992), 'M', "FNTFNC92E284151Z", "IT",
                "Pianello del Lario");

        System.out.println(elettore2);
    }
}
