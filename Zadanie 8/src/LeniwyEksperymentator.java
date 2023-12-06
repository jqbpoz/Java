import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;


public class LeniwyEksperymentator implements LeniwyBadaczKostekDoGry {

    final private Map<Integer, Map<Integer, Integer>> wynikiZadan = new HashMap<>(new HashMap<>());
    ExecutorService fabryka;

    @Override
    public void fabrykaWatkow(ExecutorService executorService) {
        this.fabryka = executorService;
    }

    @Override
    public int kostkaDoZbadania(KostkaDoGry kostka, int liczbaRzutow) {
        int id = generatorIdentyfikator(kostka);
        Runnable zadanie = () -> {
            System.out.println("Rozpoczynam test!!!");
            Map<Integer, Integer> badanie = new HashMap<>();
            for (int i = 0; i < liczbaRzutow; i++) {
                int wynik = kostka.rzut();
                if (!badanie.containsKey(wynik)) {
                    badanie.put(wynik, 1);
                } else {
                    badanie.put(wynik, badanie.get(wynik) + 1);
                }
            }
            synchronized (wynikiZadan) {
                wynikiZadan.put(id, badanie);
                System.out.println("Skończyłem testowanie");
            }
        };
        fabryka.submit(zadanie);
        return id;
    }

    @Override
    public boolean badanieKostkiZakonczono(int identyfikator) {
        synchronized (wynikiZadan) {
            return wynikiZadan.containsKey(identyfikator);
        }
    }

    @Override
    public Map<Integer, Integer> histogram(int identyfikator) {
        synchronized (wynikiZadan) {
            return wynikiZadan.get(identyfikator);
        }
    }

    private int generatorIdentyfikator(KostkaDoGry kostka) {
        return kostka.hashCode();
    }
}
