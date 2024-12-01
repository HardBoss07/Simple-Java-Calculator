module ch.bosshard.matteo.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.bosshard.matteo.calculator to javafx.fxml;
    exports ch.bosshard.matteo.calculator;
}