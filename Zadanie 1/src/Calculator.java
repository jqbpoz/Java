//The program passed all the teacher's requirements

import java.util.Arrays;

class Calculator extends CalculatorOperations {

    int acc;
    int stackIndex;

    int[] memory = new int[CalculatorOperations.MEMORY_SIZE];
    int[] stack = new int[CalculatorOperations.STACK_SIZE];

    public Calculator() {
        this.acc = 0;
        this.stackIndex = 0;
    }

    @Override
    public void setAccumulator(int value) {
        this.acc = value;
    }

    @Override
    public int getAccumulator() {
        return this.acc;
    }

    @Override
    public int getMemory(int index) {
        return this.memory[index];
    }

    @Override
    public void accumulatorToMemory(int index) {
        this.memory[index] = this.acc;
    }

    @Override
    public void addToAccumulator(int value) {
        this.acc += value;
    }

    @Override
    public void subtractFromAccumulator(int value) {
        this.acc -= value;
    }

    @Override
    public void addMemoryToAccumulator(int index) {
        this.acc += this.memory[index];
    }

    @Override
    public void subtractMemoryFromAccumulator(int index) {
        this.acc -= this.memory[index];
    }

    @Override
    public void reset() {
        Arrays.fill(this.memory, 0);
        Arrays.fill(this.stack, 0);
        acc = 0;
        stackIndex = 0;
    }

    @Override
    public void exchangeMemoryWithAccumulator(int index) {
        int buffer = memory[index];
        memory[index] = acc;
        acc = buffer;
    }

    @Override
    public void pushAccumulatorOnStack() {
        this.stack[this.stackIndex] = acc;
        this.stackIndex++;
    }

    @Override
    public void pullAccumulatorFromStack() {
        this.stackIndex--;
        this.acc = this.stack[this.stackIndex];
        this.stack[this.stackIndex] = 0;
    }
}
