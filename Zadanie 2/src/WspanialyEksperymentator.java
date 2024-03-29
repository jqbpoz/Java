import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class WspanialyEksperymentator implements Eksperymentator {

    KostkaDoGry kostka;
    long czasEksperymentu;

    @Override
    public void użyjKostki(KostkaDoGry kostka) {
        this.kostka = kostka;
    }

    @Override
    public void czasJednegoEksperymentu(long czasEksperymentu) {
        this.czasEksperymentu = czasEksperymentu;
    }

    @Override
    public Map<Integer, Double> szansaNaWyrzucenieOczek(int liczbaKostek) {
        Map<Integer, Double> mapa = new HashMap<>();
        for (int i = 1; i <= (6 * liczbaKostek); i++) {
            mapa.put(i, 0.0);
        }
        long czasRozpoczecia = System.currentTimeMillis();
        int omega = 0;
        while (true) {
            int key = 0;
            for (int j = 0; j < liczbaKostek; j++) {
                key += kostka.rzut();
            }
            mapa.put(key, mapa.get(key) + 1);
            omega += 1;
            long obecnyCzas = System.currentTimeMillis();
            if (obecnyCzas - czasRozpoczecia >= this.czasEksperymentu) {
                int finalOmega = omega > 0 ? omega : 1;
                mapa.forEach((klucz, wartosc) -> mapa.put(klucz, wartosc / finalOmega));
                break;
            }
        }
        return mapa;
    }

    @Override
    public double szansaNaWyrzucenieKolejno(List<Integer> sekwencja) {
        double zdarzenie = 0;
        double omega = 0;
        int rozmiar = sekwencja.size();
        long czasRozpoczecia = System.currentTimeMillis();
        while (true) {
            omega += 1;
            int zdarzeniaSekwencja = 0;
            for (int i = 0; i < rozmiar; i++) {
                int rzut = kostka.rzut();
                if (rzut == sekwencja.get(i)) {
                    zdarzeniaSekwencja++;
                }
            }
            if (zdarzeniaSekwencja == rozmiar) {
                zdarzenie++;
            }
            long obecnyCzas = System.currentTimeMillis();
            if (obecnyCzas - czasRozpoczecia >= this.czasEksperymentu) {
                break;
            }
        }
        return zdarzenie / omega;
    }

    @Override
    public double szansaNaWyrzucenieWDowolnejKolejności(Set<Integer> oczka) {
        Set<Integer> tymczasowySet = new HashSet<>();
        double zdarzenie = 0;
        double omega = 0;
        int rozmiar = oczka.size();
        long czasRozpoczecia = System.currentTimeMillis();
        while (true) {
            omega += 1;
            for (int i = 0; i < rozmiar; i++) {
                tymczasowySet.add(kostka.rzut());
            }
            if (tymczasowySet.equals(oczka)) {
                zdarzenie += 1;
            }
            tymczasowySet.clear();
            long obecnyCzas = System.currentTimeMillis();
            if (obecnyCzas - czasRozpoczecia >= this.czasEksperymentu) {
                break;
            }
        }
        return zdarzenie / omega;
    }
}
