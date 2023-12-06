import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;


class LeniwyEksperymentator implements LeniwyBadaczKostekDoGry {

    final private Map<Integer, Map<Integer, Integer>> wynikiZadan = new HashMap<>(new HashMap<>());
    ExecutorService fab;

    @Override
    public void fabrykaWatkow(ExecutorService executorService) {
        this.fab = executorService;
    }

    void wykonajZadanie(KostkaDoGry kostka, int liczbaRzutow, int id) {
        Runnable zadanie = () -> {
//            System.out.println("Rozpoczynam test!!!");
            Map<Integer, Integer> badanie = new HashMap<>();
            for (int i = 0; i < liczbaRzutow; i++) {
                int wynik = kostka.rzut();
                if (!badanie.containsKey(wynik)) {
                    badanie.put(wynik, 1);
                } else {
                    badanie.put(wynik, badanie.get(wynik) + 1);
                }
            }
            wstawWynik(id, badanie);
        };
        dodajDoKolejki(zadanie);
    }

    void wstawWynik(int id, Map<Integer, Integer> badanie) {
        synchronized (wynikiZadan) {
            wynikiZadan.put(id, badanie);
//            System.out.println("Skończyłem testowanie");
        }
    }

    void dodajDoKolejki(Runnable zadanie) {
        fab.submit(zadanie);
    }

    @Override
    public int kostkaDoZbadania(KostkaDoGry kostka, int liczbaRzutow) {
        int id = generatorIdentyfikator(kostka);
        wykonajZadanie(kostka, liczbaRzutow, id);
        return id;
    }

    boolean prawda() {
        return true;
    }

    boolean niePrawda() {
        return false;
    }

    @Override
    public boolean badanieKostkiZakonczono(int identyfikator) {
        synchronized (wynikiZadan) {
            if (wynikiZadan.containsKey(identyfikator)) {
                return prawda();
            } else {
                return niePrawda();
            }
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