import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculator calc1 = new Calculator();
        display(calc1);
        calc1.setAccumulator(5);
        calc1.accumulatorToMemory(0);
        calc1.getMemory(0);
        display(calc1);
        calc1.addToAccumulator(70);
        calc1.subtractFromAccumulator(40);
        calc1.addMemoryToAccumulator(0);
        display(calc1);
        calc1.subtractFromAccumulator(5);
        calc1.subtractMemoryFromAccumulator(0);
        display(calc1);
        calc1.exchangeMemoryWithAccumulator(0);
        display(calc1);
        calc1.reset();
        display(calc1);
        calc1.setAccumulator(50);
        calc1.pushAccumulatorOnStack();
        calc1.setAccumulator(20);
        calc1.pushAccumulatorOnStack();
        calc1.setAccumulator(10);
        calc1.pushAccumulatorOnStack();
        calc1.setAccumulator(30);
        calc1.pushAccumulatorOnStack();
        display(calc1);
        calc1.pullAccumulatorFromStack();
        display(calc1);
        calc1.pushAccumulatorOnStack();
        display(calc1);
        calc1.pullAccumulatorFromStack();
        calc1.pullAccumulatorFromStack();
        calc1.pullAccumulatorFromStack();
        calc1.pullAccumulatorFromStack();
        display(calc1);
    }


    public static void display(Calculator calc) {
        System.out.println("ACC: " + calc.acc);
        System.out.println(Arrays.toString(calc.memory));
        System.out.println(Arrays.toString(calc.stack));
        System.out.println("STACK INDEX: " + calc.stackIndex);
    }
}