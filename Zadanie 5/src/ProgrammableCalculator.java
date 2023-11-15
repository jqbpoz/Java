import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProgrammableCalculator implements ProgrammableCalculatorInterface {

    private BufferedReader programCodeReader;
    private LineReader stdin;
    private LinePrinter stdout;
    private Map<Integer, String> programCodeMap;
    private Map<String, Integer> variablesMap;

    public ProgrammableCalculator() {
        programCodeMap = new HashMap<>();
        variablesMap = new HashMap<>();
    }

    @Override
    public void programCodeReader(BufferedReader reader) {
        programCodeReader = reader;
    }

    @Override
    public void setStdin(LineReader input) {
        stdin = input;
    }

    @Override
    public void setStdout(LinePrinter output) {
        stdout = output;
    }

    @Override
    public void run(int line) {
        loadProgramCode();
        executeProgram(line);
    }

    private void loadProgramCode() {
        try {
            String line;
            int lineNumber = 1;
            while (true) {
                line = programCodeReader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    break;
                }
                programCodeMap.put(lineNumber, line);
                lineNumber++;
            }
        } catch (IOException e) {
        }
    }

    private void executeProgram(int line) {
        int lineKey = line;
        while (programCodeMap.containsKey(lineKey)) {
            String lineInstructionString = programCodeMap.get(lineKey);
            processCodeLine(lineInstructionString);
            lineKey++;
        }
    }


    private void processCodeLine(String codeLine) {
        // codeLine ma postać "10 Let i = 3"
        String[] dividedCodeLine = codeLine.split(" ", 3);
        //comand = "let"
        String command = dividedCodeLine[1].toUpperCase();
        //expresion = "i = 3" chyba że to to end;
        String expresion = (dividedCodeLine.length > 2) ? dividedCodeLine[2] : command;

        switch (command) {
            case "LET":
                let(expresion);
                break;
            case "PRINT":
                print(expresion);
                break;
            case "GOTO":
                gotoFunc(expresion);
                break;
            case "END":
                break;
            case "IF":
                ifFunc(expresion);
                break;
            case "INPUT":
                processInput(expresion);
                break;
        }
    }

    private void let(String expression) {
        String[] parts = expression.split("=", 2);
        variablesMap.put(parts[0].trim().toUpperCase(), evaluateExpression(parts[1].trim()));
    }

    private void print(String expression) {
        if (expression.startsWith("\"") && expression.endsWith("\"")) {
            stdout.printLine(expression);
        } else {
            String output = evaluateExpression(expression) + "";
            stdout.printLine(output);
        }

    }

    private void gotoFunc(String line) {
        int targetLine = Integer.parseInt(line);
        executeProgram(targetLine);
    }

    private void ifFunc(String ifExpresion) {
        String[] parts = ifExpresion.split("(?i) goto ");
        String condition = parts[0];
        String newLine = parts[1];
        if (evaluateExpression(condition) == 1) {
            gotoFunc(newLine);
        }

    }

    private void processInput(String variableName) {
        String inputLine = stdin.readLine();
        int value = Integer.parseInt(inputLine.trim());
        variablesMap.put(variableName.trim().toUpperCase(), value);
    }

    private int evaluateExpression(String expression) {
        String[] expressionArr = expression.split(" ");
        if (expression.matches("\\d+")) {
            return Integer.parseInt(expression);
        }
        if (expressionArr.length == 1)
            return getValueOf(expression);
        else {
            String operator = expressionArr[1];
            int left = getValueOf(expressionArr[0]);
            int rigth = getValueOf(expressionArr[2]);
            switch (operator) {
                case "+":
                    return left + rigth;
                case "-":
                    return left - rigth;
                case "*":
                    return left * rigth;
                case "/":
                    return left / rigth;
                case ">": {
                    if (left > rigth) {
                        return 1;
                    }
                    return -1;
                }
                case "<": {
                    if (left < rigth) {
                        return 1;
                    }
                    return -1;
                }
                case "=": {
                    if (left == rigth) {
                        return 1;
                    }
                    return -1;
                }

            }
        }

        return -100;
    }


    private int getValueOf(String symbol) {
        if (isNumeric(symbol)) {
            return Integer.parseInt(symbol);
        } else {
            return variablesMap.get(symbol.toUpperCase());
        }
    }

    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}


