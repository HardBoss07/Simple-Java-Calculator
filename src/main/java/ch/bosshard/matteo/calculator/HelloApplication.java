package ch.bosshard.matteo.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Title label
        Label title = new Label("Simple Calculator");

        // Calculator Output TextField
        TextField output = new TextField("0");
        output.getStyleClass().add("output-label");
        output.setEditable(false); // Prevent editing by user
        output.setAlignment(javafx.geometry.Pos.CENTER_RIGHT); // Align text to the right
        output.setMaxWidth(Double.MAX_VALUE);  // Allow the text field to stretch across the width of the container

        //Equation Label
        TextField equationLabel = new TextField("No current equation");
        equationLabel.getStyleClass().add("equation-label");
        equationLabel.setEditable(false);
        equationLabel.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        equationLabel.setMaxWidth(Double.MAX_VALUE);

        //All buttons
        Button clearBtn = new Button("C");
        Button deleteBtn = new Button("←");
        Button zeroBtn = new Button("0");
        Button oneBtn = new Button("1");
        Button twoBtn = new Button("2");
        Button threeBtn = new Button("3");
        Button fourBtn = new Button("4");
        Button fiveBtn = new Button("5");
        Button sixBtn = new Button("6");
        Button sevenBtn = new Button("7");
        Button eightBtn = new Button("8");
        Button nineBtn = new Button("9");
        Button commaBtn = new Button(".");
        Button addBtn = new Button("+");
        Button subtractBtn = new Button("-");
        Button multiplyBtn = new Button("*");
        Button divideBtn = new Button("/");
        Button equalsBtn = new Button("=");

        // Number Button Actions
        Calculator calc = new Calculator();
        Map<Button, String> numberButtons = new HashMap<>();
        numberButtons.put(zeroBtn, "0");
        numberButtons.put(oneBtn, "1");
        numberButtons.put(twoBtn, "2");
        numberButtons.put(threeBtn, "3");
        numberButtons.put(fourBtn, "4");
        numberButtons.put(fiveBtn, "5");
        numberButtons.put(sixBtn, "6");
        numberButtons.put(sevenBtn, "7");
        numberButtons.put(eightBtn, "8");
        numberButtons.put(nineBtn, "9");
        numberButtons.put(commaBtn, ".");

        numberButtons.forEach((button, value) ->
                button.setOnAction(e -> {
                    calc.currentNumber += value;
                    output.setText(calc.getCurrentNumber());
                    equationLabel.setText(calc.getCurrentEquation());

                })
        );

        // Operator Button Actions
        Map<Button, String> operatorButtons = new HashMap<>();
        operatorButtons.put(addBtn, "+");
        operatorButtons.put(subtractBtn, "-");
        operatorButtons.put(multiplyBtn, "*");
        operatorButtons.put(divideBtn, "/");

        operatorButtons.forEach((button, operator) ->
                button.setOnAction(e -> {
                    calc.allNumbers.add(calc.currentNumber);
                    calc.currentNumber = "";
                    calc.allOperations.add(operator);
                    equationLabel.setText(calc.getCurrentEquation());

                })
        );

        // Equals Button Action
        equalsBtn.setOnAction(e -> {
            calc.allNumbers.add(calc.currentNumber);
            calc.currentNumber = "";
            calc.calculateAll();
            output.setText(calc.result);
            System.out.println(calc.allNumbers);
            System.out.println(calc.allOperations);
            equationLabel.setText(calc.getCurrentEquation());
        });

        // Clear Button Action
        clearBtn.setOnAction(e -> {
            calc.result = "NULL";
            calc.allOperations.clear();
            calc.allNumbers.clear();
            calc.currentNumber = "";
            output.setText("0");
            equationLabel.setText("No current equation");
        });

        //Delete Button Action
        deleteBtn.setOnAction(e -> {
            calc.undoLastAdded();
            equationLabel.setText(calc.currentEquation);
            output.setText(calc.getCurrentNumber());
        });

        //Layout
        HBox firstRow = new HBox(10, sevenBtn, eightBtn, nineBtn, divideBtn);
        HBox secondRow = new HBox(10, fourBtn, fiveBtn, sixBtn, multiplyBtn);
        HBox thirdRow = new HBox(10, oneBtn, twoBtn, threeBtn, subtractBtn);
        HBox fourthRow = new HBox(10, commaBtn, zeroBtn, equalsBtn, addBtn);

        HBox clearRow = new HBox();
        clearRow.getChildren().addAll(clearBtn, deleteBtn);
        clearRow.setSpacing(10);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(title, output, equationLabel, clearRow, firstRow, secondRow, thirdRow, fourthRow);

        //Scene handeling
        Scene scene = new Scene(mainLayout, 350, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Simple Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}