package ch.bosshard.matteo.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Title label
        Label title = new Label("Simple Calculator");

        //Calculator Output label
        Label output = new Label("0");

        //All buttons
        Button clearBtn = new Button("C");
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

        //All button actions

        //Layout
        HBox firstRow = new HBox(10, sevenBtn, eightBtn, nineBtn, divideBtn);
        HBox secondRow = new HBox(10, fourBtn, fiveBtn, sixBtn, multiplyBtn);
        HBox thirdRow = new HBox(10, oneBtn, twoBtn, threeBtn, subtractBtn);
        HBox fourthRow = new HBox(10, commaBtn, zeroBtn, equalsBtn, addBtn);

        HBox clearRow = new HBox();
        clearRow.getChildren().add(clearBtn);
        clearRow.setSpacing(10);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(title, output, clearRow, firstRow, secondRow, thirdRow, fourthRow);

        //Scene handeling
        Scene scene = new Scene(mainLayout, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Simple Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}