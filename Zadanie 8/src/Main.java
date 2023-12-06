import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {

        LeniwyEksperymentator eksperymentator = new LeniwyEksperymentator();
        ExecutorService mojaFabryka = Executors.newFixedThreadPool(3);

        KostkaDoGry prawdziwaKostka = new PrawdziwaKostka();

        eksperymentator.fabrykaWatkow(mojaFabryka);
        int id1 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id2 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id3 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id4 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id5 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id6 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id7 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id8 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id9 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id10 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id11 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id12 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id13 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id14 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id15 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id16 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id17 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id18 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id19 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        while (true) {
            if (eksperymentator.badanieKostkiZakonczono(id1)) {
                System.out.println(eksperymentator.histogram(id1));
                break;
            }

        }
        mojaFabryka.shutdown();
    }
}