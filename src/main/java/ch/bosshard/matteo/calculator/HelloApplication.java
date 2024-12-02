package ch.bosshard.matteo.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //TODO change css to be more modern
        //TODO make first number possible to be negative
        //TODO make if i press comma with no number before it adds a zero: .25 -> 0.25

        //Title label
        Label title = new Label("Simple Calculator");
        title.getStyleClass().add("title-label");

        //Calculator Output TextField
        TextField output = new TextField("0");
        output.getStyleClass().add("output-label");
        output.setEditable(false);
        output.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        output.setMaxWidth(Double.MAX_VALUE);

        //Equation Label
        TextField equationLabel = new TextField("No current equation");
        equationLabel.getStyleClass().add("equation-label");
        equationLabel.setEditable(false);
        equationLabel.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        equationLabel.setMaxWidth(Double.MAX_VALUE);

        //EquationLog Dropdown
        ComboBox equationLogDropdown = new ComboBox();
        equationLogDropdown.setPromptText("Previous Equations");
        equationLogDropdown.getStyleClass().add("equation-log");
        equationLogDropdown.setPlaceholder(new Text("No equations yet"));

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

        //Button Styles
        clearBtn.getStyleClass().add("button-other");
        deleteBtn.getStyleClass().add("button-other");
        zeroBtn.getStyleClass().add("button-number");
        oneBtn.getStyleClass().add("button-number");
        twoBtn.getStyleClass().add("button-number");
        threeBtn.getStyleClass().add("button-number");
        fourBtn.getStyleClass().add("button-number");
        fiveBtn.getStyleClass().add("button-number");
        sixBtn.getStyleClass().add("button-number");
        sevenBtn.getStyleClass().add("button-number");
        eightBtn.getStyleClass().add("button-number");
        nineBtn.getStyleClass().add("button-number");
        commaBtn.getStyleClass().add("button-number");
        addBtn.getStyleClass().add("button-operator");
        subtractBtn.getStyleClass().add("button-operator");
        multiplyBtn.getStyleClass().add("button-operator");
        divideBtn.getStyleClass().add("button-operator");
        equalsBtn.getStyleClass().add("button-operator");

        //Number Button Actions
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

        //Operator Button Actions
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

        //Equals Button Action
        equalsBtn.setOnAction(e -> {
            calc.allNumbers.add(calc.currentNumber);
            calc.currentNumber = "";
            calc.calculateAll();
            output.setText(calc.result);
            equationLabel.setText(calc.getCurrentEquation() + " = " + calc.result);
            calc.equationLog.add(equationLabel.getText());
            System.out.println("Equation Log: " + calc.equationLog);
            System.out.println("All numbers of current Equation: " + calc.allNumbers);
            System.out.println("All operations of current Equation: " + calc.allOperations);

            calc.allNumbers.clear();
            calc.allOperations.clear();
            calc.currentNumber = calc.result;

            equationLogDropdown.getItems().setAll(calc.equationLog);
        });

        //Clear Button Action
        clearBtn.setOnAction(e -> {
            calc.result = "NULL";
            calc.allOperations.clear();
            calc.allNumbers.clear();
            calc.equationLog.clear();
            calc.currentNumber = "";
            output.setText("0");
            equationLabel.setText("No current equation");
            equationLogDropdown.getItems().clear();
            equationLogDropdown.setPlaceholder(new Text("No equations yet"));
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
        mainLayout.getChildren().addAll(title, output, equationLabel, clearRow, firstRow, secondRow, thirdRow, fourthRow, equationLogDropdown);

        //Scene handling
        Scene scene = new Scene(mainLayout, 350, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Simple Java Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}