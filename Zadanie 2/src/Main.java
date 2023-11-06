import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 4);
        Set<Integer> set1 = new HashSet<>(list1);
        KostkaDoGry kostkaRef = new PrawdziwaKostka();
        Eksperymentator ref = new WspanialyEksperymentator();
        ref.użyjKostki(kostkaRef);
        ref.czasJednegoEksperymentu(500);
        ref.szansaNaWyrzucenieOczek(2).forEach((klucz, wartosc) -> {
            System.out.println("Key: " + klucz + ", Value: " + wartosc);
        });
        System.out.println("Kolejno " + ref.szansaNaWyrzucenieKolejno(list1));
        System.out.println("Dowolnie " + ref.szansaNaWyrzucenieWDowolnejKolejności(set1));

    }
}
