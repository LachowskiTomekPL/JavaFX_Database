package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLOutput;

public class menuController {
    @FXML
    private Button przycisk;
    @FXML
    private TextField nameField;


    @FXML
    void initialize() {

        System.out.println(nameField);
        EventHandler<ActionEvent> actionEventEventHandler = event -> System.out.println("Działa!!!");
        przycisk.addEventHandler(ActionEvent.ACTION, actionEventEventHandler);


        EventHandler<MouseEvent> mouseEventEventHandler = event -> System.out.println("Myszka działą !!!!");


        przycisk.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEventEventHandler);

    }


}
