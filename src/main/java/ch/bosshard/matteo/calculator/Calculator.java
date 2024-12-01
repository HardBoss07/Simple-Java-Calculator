package ch.bosshard.matteo.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public String currentNumber = "";
    public List<String> allNumbers = new ArrayList<String>();
    public List<String> allOperations = new ArrayList<String>();
    public String result = "NULL";
    public String currentEquation = "";

    public Calculator() {

    }

    public void undoLastAdded() {
        // Ensure the equation is not empty
        if (!currentEquation.isEmpty()) {
            while (currentEquation.endsWith(" ")) {
                currentEquation = currentEquation.substring(0, currentEquation.length() - 1);
            }

            char lastChar = currentEquation.charAt(currentEquation.length() - 1);

            currentEquation = currentEquation.substring(0, currentEquation.length() - 1);

            if (Character.isDigit(lastChar) || lastChar == '.') {
                if (!currentNumber.isEmpty()) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                }
            } else {
                if (!allOperations.isEmpty()) {
                    allOperations.removeLast();
                }

                if (!allNumbers.isEmpty()) {
                    currentNumber = allNumbers.removeLast();
                } else {
                    currentNumber = "";
                }
            }

            if (currentNumber.isEmpty() && !allNumbers.isEmpty()) {
                currentNumber = allNumbers.getLast();
            }

            if (currentEquation.isEmpty()) {
                resetCalculator();
            }
        }
    }

    private void resetCalculator() {
        currentEquation = "";
        currentNumber = "";
        allNumbers.clear();
        allOperations.clear();
    }

    public String getCurrentEquation() {
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < allNumbers.size(); i++) {
            equation.append(allNumbers.get(i) + " ");
            if (i < allOperations.size()) {
                equation.append(allOperations.get(i) + " ");
            }
        }
        equation.append(currentNumber);
        currentEquation = equation.toString();
        return equation.toString();
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void calculateAll() {
        if (correctNumberAndOperationAmount()) {
            try {
                String[] parts = currentEquation.split(" ");
                System.out.println("Parts: " + Arrays.toString(parts));

                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].equals("*") || parts[i].equals("/")) {
                        double prevNum = Double.parseDouble(parts[i - 1]);
                        double nextNum = Double.parseDouble(parts[i + 1]);
                        double resultPart = 0;

                        if (parts[i].equals("*")) {
                            resultPart = prevNum * nextNum;
                        } else if (parts[i].equals("/")) {
                            if (nextNum == 0) {
                                result = "#ERROR#";
                                return;
                            }
                            resultPart = prevNum / nextNum;
                        }

                        parts[i - 1] = String.valueOf(resultPart);
                        for (int j = i; j < parts.length - 2; j++) {
                            parts[j] = parts[j + 2];
                        }
                        parts = Arrays.copyOf(parts, parts.length - 2);
                        i--;
                    }
                }

                double tempResult = Double.parseDouble(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    String operator = parts[i];
                    double number = Double.parseDouble(parts[i + 1]);
                    i++;

                    switch (operator) {
                        case "+":
                            tempResult += number;
                            break;
                        case "-":
                            tempResult -= number;
                            break;
                        default:
                            result = "#ERROR#";
                            return;
                    }
                }

                result = Double.toString(tempResult);
                currentEquation += " = " + result;
                System.out.println("Result: " + result);
            } catch (Exception e) {
                result = "#ERROR#";
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            result = "#ERROR#";
        }
    }

    private boolean correctNumberAndOperationAmount() {
        allNumbers.removeIf(String::isEmpty);

        return allNumbers.size() == allOperations.size() + 1;
    }

}
