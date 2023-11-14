import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
 public static void main(String[] args) {
        try {
            // Otwórz plik do odczytu
            String filePath = "./src/in.txt";
            FileReader fileReader = new FileReader(filePath);

            // Przekaz plik do BufferedReader
            BufferedReader programCodeReader = new BufferedReader(fileReader);

            // Tworzymy instancję kalkulatora
            ProgrammableCalculator calculator = new ProgrammableCalculator();

            // Konfigurujemy obiekty LineReader i LinePrinter
            ProgrammableCalculatorInterface.LineReader lineReader = () -> {
                // Implementuj odpowiednią logikę odczytu z konsoli lub innych źródeł
                return null;
            };

            ProgrammableCalculatorInterface.LinePrinter linePrinter = System.out::println;

            // Ustawiamy obiekty LineReader i LinePrinter w kalkulatorze
            calculator.programCodeReader(programCodeReader);
            calculator.setStdin(lineReader);
            calculator.setStdout(linePrinter);

            // Uruchamiamy program od linii 1
            calculator.run(1);

            // Zamykamy BufferedReader po zakończeniu pracy
            programCodeReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}