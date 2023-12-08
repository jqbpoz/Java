// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        WatkowyEksperymentator eksperymentator = new WatkowyEksperymentator();
        ThreadFactory mojaFabryka = new MojaFabryka();
        KostkaDoGry prawdziwaKostka = new PrawdziwaKostka();

        eksperymentator.dozwolonaLiczbaDzialajacychWatkow(3);
        eksperymentator.fabrykaWatkow(mojaFabryka);
        int id1 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 2);
        int id2 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 40);
        int id3 = eksperymentator.kostkaDoZbadania(prawdziwaKostka, 400);
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
        while (true) {
            if (eksperymentator.badanieKostkiZakonczono(id2)) {
                System.out.println(eksperymentator.histogram(id2));
                break;
            }

        }
        while (true) {
            if (eksperymentator.badanieKostkiZakonczono(id3)) {
                System.out.println(eksperymentator.histogram(id3));
                break;
            }

        }


    }
}