import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class ProgrammableCalculator implements ProgrammableCalculatorInterface {

    private BufferedReader programCodeReader;
    private LineReader stdin;
    private LinePrinter stdout;
    private int fakeNumberLine;
    private Map<String, Integer> convertRealToFakeMap;
    private Map<Integer, String> programCodeMap;
    private Map<String, Integer> variablesMap;

    public ProgrammableCalculator() {
        programCodeMap = new HashMap<>();
        variablesMap = new HashMap<>();
        convertRealToFakeMap = new HashMap<>();
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
        this.fakeNumberLine = convertRealToFakeMap.get(line + "");
        while (programCodeMap.containsKey(this.fakeNumberLine)) {
            String lineInstructionString = programCodeMap.get(this.fakeNumberLine);
            if (!processCodeLine(lineInstructionString)) {
                break;
            }
            ;
            this.fakeNumberLine++;
        }
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
                convertRealToFakeMap.put(line.split(" ")[0].trim(), lineNumber);
                programCodeMap.put(lineNumber, line);
                lineNumber++;
            }
        } catch (IOException e) {
        }
    }

    private void executeProgram(int line) {

    }


    private boolean processCodeLine(String codeLine) {
        // codeLine ma postać "10 Let i = 3"
        String[] dividedCodeLine = codeLine.split(" ", 3);
        //comand = "useLet"
        String command = dividedCodeLine[1].toUpperCase();
        //expresion = "i = 3" chyba że to to end;
        String expresion = (dividedCodeLine.length > 2) ? dividedCodeLine[2] : command;

        switch (command) {
            case "LET":
                useLet(expresion);
                break;
            case "PRINT":
                usePrint(expresion);
                break;
            case "GOTO":
                useGoto(expresion);
                break;
            case "END":
                System.out.println("jestem tutaj");
                return false;
            case "IF":
                useIf(expresion);
                break;
            case "INPUT":
                useInput(expresion);
                break;
        }
        return true;
    }

    private void useLet(String expression) {
        String[] parts = expression.split("=", 2);
        variablesMap.put(parts[0].trim().toUpperCase(), evaluateExpression(parts[1].trim()));
    }

    private void usePrint(String expression) {
        if (expression.startsWith("\"") && expression.endsWith("\"")) {
            stdout.printLine(expression.substring(1, expression.length() - 1));
        } else {
            String output = evaluateExpression(expression) + "";
            stdout.printLine(output);
        }

    }

    private void useGoto(String line) {
        this.fakeNumberLine = convertRealToFakeMap.get(line) - 1;
    }

    private void useIf(String ifExpresion) {
        String[] parts = ifExpresion.split("(?i) goto ");
        String condition = parts[0];
        String newLine = parts[1];
        if (evaluateExpression(condition) == 1) {
            useGoto(newLine);
        }

    }

    private void useInput(String variableName) {
        int value = Integer.parseInt(stdin.readLine().trim());
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



