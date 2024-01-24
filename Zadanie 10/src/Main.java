import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader("./src/in1.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("./src/in2.txt"));
            BufferedReader reader3 = new BufferedReader(new FileReader("./src/in3.txt"));
            BufferedReader reader4 = new BufferedReader(new FileReader("./src/in4.txt"));
            BufferedReader reader5 = new BufferedReader(new FileReader("./src/in5.txt"));
            BufferedReader reader6 = new BufferedReader(new FileReader("./src/in6.txt"));
            ProgrammableCalculator calculator1 = new ProgrammableCalculator();
            ProgrammableCalculator calculator2 = new ProgrammableCalculator();
            ProgrammableCalculator calculator3 = new ProgrammableCalculator();
            ProgrammableCalculator calculator4 = new ProgrammableCalculator();
            ProgrammableCalculator calculator5 = new ProgrammableCalculator();
            ProgrammableCalculator calculator6 = new ProgrammableCalculator();

//            ProgrammableCalculatorInterface.LineReader lineReader = () -> {
//                Scanner scanner = new Scanner(System.in);
//                System.out.print("Podaj Int");
//                String userInput = scanner.nextLine();
//                scanner.close();
//                return userInput;
//            };
            ProgrammableCalculatorInterface.LineReader lineReader = () -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Podaj Int: ");
                String userInput = "";

                if (scanner.hasNextLine()) {
                    userInput = scanner.nextLine();
                }

//                scanner.close();
                return userInput;
            };

            ProgrammableCalculatorInterface.LinePrinter linePrinter = System.out::println;
            calculator1.setStdout(linePrinter);
            calculator1.setStdin(lineReader);
            calculator2.setStdout(linePrinter);
            calculator2.setStdin(lineReader);
            calculator3.setStdout(linePrinter);
            calculator3.setStdin(lineReader);
            calculator4.setStdout(linePrinter);
            calculator4.setStdin(lineReader);
            calculator5.setStdout(linePrinter);
            calculator5.setStdin(lineReader);
            calculator6.setStdout(linePrinter);
            calculator6.setStdin(lineReader);

//            System.out.println("Program NR1 Sprawdzenie operacji");
//            calculator1.programCodeReader(reader1);
//            calculator1.run(190);
//            reader1.close();
//            System.out.println("\nexpected:\n6\n12\n3\n4\n8\nSTRING\n");
//
//            System.out.println("Program NR2 Inna numeracja linii");
//            calculator2.programCodeReader(reader2);
//            calculator2.run(190);
//            reader2.close();
//            System.out.println("\nexpected:\n6\n12\n3\n4\n8\nSTRING\n");
//
//            System.out.println("Program NR3 Goto bez IF");
//            calculator3.programCodeReader(reader3);
//            calculator3.run(10);
//            reader3.close();
//            System.out.println("\nexpected:\nstart\nlinia 11 skok do 30\nskoczyłem z 12 do 30\n");

            System.out.println("Program NR4 Test Goto z IF");
            calculator4.programCodeReader(reader4);
            calculator4.run(10);
            reader4.close();
            System.out.println("\nexpected:\na < b\nc > d\ne = f\na nie jest < od b\nc nie jest > od d\ne !=f\n");

//            System.out.println("Program NR5");
//            calculator5.programCodeReader(reader5);
//            calculator5.run(10);
//            reader5.close();

            System.out.println("Program NR6 Test GoSub");
            calculator6.programCodeReader(reader6);
            calculator6.run(10);
            reader6.close();
            System.out.println("\nexpected:\na < b\nc > d\ne = f\na nie jest < od b\nc nie jest > od d\ne !=f\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}