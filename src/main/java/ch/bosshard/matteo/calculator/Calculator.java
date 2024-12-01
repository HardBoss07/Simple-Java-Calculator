package ch.bosshard.matteo.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public String currentNumber = "";
    public List<String> allNumbers = new ArrayList<String>();
    public List<String> allOperations = new ArrayList<String>();
    public String result = "NULL";

    public Calculator() {

    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void calculateAll() {
        if (correctNumberAndOperationAmount()) {

        }
        else {
            result = "#ERROR#";
        }
    }

    private boolean correctNumberAndOperationAmount() {
        allNumbers.removeIf(String::isEmpty);

        return allNumbers.size() == allOperations.size() + 1;
    }

}
