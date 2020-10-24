package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class menuController {

    public void initialize() {
//        System.out.println(nameField);
//        EventHandler<ActionEvent> actionEventEventHandler = event -> System.out.println("Działa!!!");
//        przycisk.addEventHandler(ActionEvent.ACTION, actionEventEventHandler);
//
//
//        EventHandler<MouseEvent> mouseEventEventHandler = event -> System.out.println("Myszka działą !!!!");
//
//
//        przycisk.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEventEventHandler);
    }

    public void userList(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/user.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void laptopList(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/laptop.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void exitButton(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.close();
    }
}
