/**
 * Klasa bazowa dla kalkulatora.
 * Kalkulator wykonuje proste operacje na liczbach caĹkowitych.
 * WyposaĹźony jest w pamiÄÄ o rozmiarze MEMORY_SIZE pozycji
 * oraz stos o rozmiarze STACK_SIZE. PoczÄtkowy stan
 * akumulatora i pamiÄci to zera.
 */
abstract public class CalculatorOperations {

    /**
     * Rozmiar pamiÄci
     */
    public static final int MEMORY_SIZE = 16;

    /**
     * GĹÄbokoĹÄ stosu
     */
    public static final int STACK_SIZE = 4;

    /**
     * Zapisuje podanÄ wartoĹÄ w akumulatorze.
     *
     * @param value wartoĹÄ do zapisania w akumulatorze
     */
    abstract public void setAccumulator(int value);

    /**
     * Zwraca wartoĹÄ zapisanÄ w akumulatorze
     *
     * @return zawartoĹÄ akumulatora
     */
    abstract public int getAccumulator();

    /**
     * Zwraca zawartoĹÄ pamiÄci na pozycji index.
     *
     * @param index pozycja w pamiÄci
     * @return wartoĹÄ znajdujÄca siÄ pod wskazanym indeksem
     */
    abstract public int getMemory(int index);

    /**
     * Zapisuje zawartoĹÄ akumulatora pod pozycjÄ index pamiÄci
     *
     * @param index pozycja w pamiÄci
     */
    abstract public void accumulatorToMemory(int index);

    /**
     * Do akumulatora dodaje przekazanÄ wartoĹÄ
     *
     * @param value wartoĹÄ do dodania do akumulatora
     */
    abstract public void addToAccumulator(int value);

    /**
     * Odejmuje przekazanÄ wartoĹÄ od akumulatora
     *
     * @param value wartoĹÄ odejmowana od akumulatora
     */
    abstract public void subtractFromAccumulator(int value);

    /**
     * Dodaje zawartoĹÄ wskazanej pozycji pamiÄci do akumulatora
     *
     * @param index pozycja w pamiÄci
     */
    abstract public void addMemoryToAccumulator(int index);

    /**
     * Odejmuje zawartoĹÄ wskazanej pozycji pamiÄci od akumulatora
     *
     * @param index pozycja w pamiÄci
     */
    abstract public void subtractMemoryFromAccumulator(int index);

    /**
     * Przywraca ustawienia poczÄtkowe - akumulator ustawiony na 0,
     * na kaĹźdej pozycji pamiÄci 0, stos pusty.
     */
    abstract public void reset();

    /**
     * Wymienia zawartoĹÄ wskazanej pozycji pamiÄci z akumulatorem
     *
     * @param index pozycja w pamiÄci
     */
    abstract public void exchangeMemoryWithAccumulator(int index);

    /**
     * Zapisuje zawartoĹÄ akumulatora na szczycie stosu. <b>ZawartoĹÄ akumulatora
     * nie ulega zmianie</b>.
     */
    abstract public void pushAccumulatorOnStack();

    /**
     * Zdejmuje ze szczytu stosu zawartoĹÄ akumulatora.
     */
    abstract public void pullAccumulatorFromStack();
}