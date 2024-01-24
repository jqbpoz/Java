import java.util.Stack;
import java.io.IOException;
import java.util.Map;
import java.io.BufferedReader;
import java.util.HashMap;

public class ProgrammableCalculator implements ProgrammableCalculatorInterface{
    private LineReader input;
    private LinePrinter output;
    private Map<String, Integer> variables;
    private BufferedReader codeReader;
    private Stack<Integer> returnStack;

    ProgrammableCalculator(){
        variables = new HashMap<>();
        returnStack = new Stack<>();
    }

    @Override
    public void programCodeReader(BufferedReader reader) {
        this.codeReader = reader;
    }

    @Override
    public void setStdin(LineReader input) {
        this.input = input;
    }

    @Override
    public void setStdout(LinePrinter output) {
        this.output = output;
    }

    private class LoadCode{

        private final Map<Integer, Integer> conventer;
        private final Map<Integer, String> codeMap;

        private LoadCode() {
            conventer = new HashMap<>();
            codeMap = new HashMap<>();
            try {
                loadOnlyCode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public Map<Integer, Integer> getConventer() {
            return conventer;
        }

        public Map<Integer, String> getCodeMap() {
            return codeMap;
        }

        private void loadOnlyCode() throws IOException {
            String line;
            int lineNumber = 1;
            while (true) {
                line = codeReader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    break;
                }
                String realLineNumb = "";
                String code;
                int i =0;
                while (true){
                    String oneChar = line.substring(i,i+1);
                    if (!oneChar.equals(" ")){
                        realLineNumb += oneChar;
                    }else {
                      code = line.substring(i+1);
                      break;
                    }
                    i++;
                }
                conventer.put(Integer.parseInt(realLineNumb),lineNumber);
                codeMap.put(lineNumber,code);
                lineNumber++;
            }
        }
    }



    private int calculator(String exp){
        String[] arr;
        arr = exp.split(" ");
        if(arr.length==1){
            return Character.isDigit(exp.charAt(0)) ? Integer.parseInt(exp) : variables.get(exp);
        }
        int left = Character.isDigit(arr[0].charAt(0)) ? Integer.parseInt(arr[0]):variables.get(arr[0].toUpperCase());
        int right = Character.isDigit(arr[2].charAt(0)) ? Integer.parseInt(arr[2]):variables.get(arr[2].toUpperCase());
        String sign = arr[1];
        if (sign.equals("*")){
            return left * right;
        }else if (sign.equals("-")){
            return left - right;
        }else if (sign.equals("/")){
            return left / right;
        }else if (sign.equals("+")){
            return left + right;
        }
        return -1;
    }

    private boolean compere(String exp){
        String[] arr;
        arr = exp.split(" ");
        int left = Character.isDigit(arr[0].charAt(0)) ? Integer.parseInt(arr[0]):variables.get(arr[0].toUpperCase());
        int right = Character.isDigit(arr[2].charAt(0)) ? Integer.parseInt(arr[2]):variables.get(arr[2].toUpperCase());
        String sign = arr[1];
        if (sign.equals(">")){
            return left > right;
        }else if (sign.equals("=")){
            return left == right;
        }else if (sign.equals("<")){
            return left < right;
        }
        return false;
    }


    @Override
    public void run(int line) {
        LoadCode currentCode = new LoadCode();
        Map<Integer, Integer> conventer = currentCode.getConventer();
        Map<Integer,String> codeMap = currentCode.getCodeMap();

        int index = conventer.get(line);
        while(codeMap.containsKey(index)){
            String currentTask = codeMap.get(index);
            String taskType = currentTask.substring(0,3).toUpperCase();
            if(taskType.equals("LET")){
                String[] arr;
                currentTask = currentTask.substring(4).toUpperCase();
                arr = currentTask.split(" ",3);
                variables.put(arr[0],calculator(arr[2]));

            }else if (taskType.equals("IF ")){
                currentTask = currentTask.substring(3);
                String[] arr;
                arr = currentTask.split("(?i) GOTO ");
                if(compere(arr[0])){
                    index = conventer.get(Integer.parseInt(arr[1]))-1;
                }
            }else if (taskType.equals("GOT")){
                currentTask = currentTask.substring(5);
                index = conventer.get(Integer.parseInt(currentTask));
                continue;

            }else if (taskType.equals("GOS")){
                currentTask = currentTask.substring(6);
                int newline = Integer.parseInt(currentTask);
                returnStack.push(index);
                index = conventer.get(newline);
                continue;
            }
            else if (taskType.equals("RET")){
                if(!returnStack.isEmpty()){
                index = returnStack.pop();
                }
            }
            else if (taskType.equals("INP")){
                currentTask = currentTask.substring(6);
                int value = Integer.parseInt(input.readLine());
                variables.put(currentTask.toUpperCase(),value);
            }
            else if (taskType.equals("PRI")){
                currentTask = currentTask.substring(6);
                if(currentTask.startsWith("\"")){
                    output.printLine(currentTask.substring(1,currentTask.length()-1));
                }else {
                    output.printLine(String.valueOf(variables.get(currentTask.toUpperCase())));
                }
            }else if (taskType.equals("END")){
                break;
            }
            index++;
        }

    }
}
