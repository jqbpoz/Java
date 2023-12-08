import java.util.HashMap;
import java.util.Map;


class WatkowyEksperymentator implements BadaczKostekDoGry {

    int limitWatkow;
    ThreadFactory fabryka;
    final private Map<Integer, Map<Integer, Integer>> wynikiZadan = new HashMap<>(new HashMap<>());
    volatile int liczbaKostekAktulanieTestowanych = 0;

    @Override
    public void dozwolonaLiczbaDzialajacychWatkow(int limitWatkow) {
        this.limitWatkow = limitWatkow;
    }

    void wykonajZadanie(KostkaDoGry kostka, int liczbaRzutow) {
        int id = generatorIdentyfikator(Thread.currentThread());
        while (!(liczbaKostekAktulanieTestowanych < limitWatkow)) {
            synchronized (wynikiZadan) {
                try {
                    wynikiZadan.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        synchronized (wynikiZadan) {
            this.liczbaKostekAktulanieTestowanych++;
        }
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
    }

    void wstawWynik(int id, Map<Integer, Integer> badanie) {
        synchronized (wynikiZadan) {
            wynikiZadan.put(id, badanie);
            wynikiZadan.notifyAll();
            this.liczbaKostekAktulanieTestowanych--;
        }
    }

    @Override
    public int kostkaDoZbadania(KostkaDoGry kostka, int liczbaRzutow) {
        Thread watek = fabryka.getThread(() -> {
                    wykonajZadanie(kostka, liczbaRzutow);
                }
        );
        int id = generatorIdentyfikator(watek);
        watek.start();
        return id;
    }

    @Override
    public void fabrykaWatkow(ThreadFactory fabryka) {
        this.fabryka = fabryka;
    }

    @Override
    public boolean badanieKostkiZakonczono(int identyfikator) {
        synchronized (wynikiZadan) {
            if (wynikiZadan.containsKey(identyfikator)) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Map<Integer, Integer> histogram(int identyfikator) {
        synchronized (wynikiZadan) {
            return wynikiZadan.get(identyfikator);
        }
    }


    private int generatorIdentyfikator(Thread watek) {
        return watek.hashCode();
    }
}
