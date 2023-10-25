public class PrawdziwaKostka implements KostkaDoGry{
    @Override
    public int rzut() {
//        System.out.println("Użyłem prawdziwej kostki");
        return (int)(Math.random()*6+1);
    }
}
