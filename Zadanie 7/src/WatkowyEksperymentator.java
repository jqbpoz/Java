import java.util.HashMap;
import java.util.Map;


class WatkowyEksperymentator implements BadaczKostekDoGry {

    int limitWatkow;
    ThreadFactory fabryka;
    private Map<Integer, Map<Integer, Integer>> wynikiZadan = new HashMap<>(new HashMap<>());
    volatile int liczbaKostekAktulanieTestowanych = 0;

    @Override
    public void dozwolonaLiczbaDzialajacychWatkow(int limitWatkow) {
        this.limitWatkow = limitWatkow;
    }

    @Override

    public int kostkaDoZbadania(KostkaDoGry kostka, int liczbaRzutow) {
        int id = generatorIdentyfikator(kostka);
        Thread watek = fabryka.getThread(() -> {
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
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Rozpoczynam test!!!");

                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Map<Integer, Integer> badanie = new HashMap<>();
                    //Czy napewno w ten sposób trzeba zapytać ???
                    badanie.put(1, 0);
                    badanie.put(2, 0);
                    badanie.put(3, 0);
                    badanie.put(4, 0);
                    badanie.put(5, 0);
                    badanie.put(6, 0);
                    for (int i = 0; i < liczbaRzutow; i++) {
                        int wynik = kostka.rzut();
                        badanie.put(wynik, badanie.get(wynik) + 1);
                    }
                    synchronized (wynikiZadan) {
                        wynikiZadan.put(id, badanie);
                        wynikiZadan.notifyAll();
                        this.liczbaKostekAktulanieTestowanych--;
                    }
                    System.out.println("Skończyłem testowanie");

                }
        );
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
