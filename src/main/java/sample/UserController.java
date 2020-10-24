package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.User;
import sample.service.UserDAO;

import java.io.IOException;


public class UserController {

    @FXML
    public TableView<User> userTableView;

    @FXML
    public TextField firstNameField, lastNameField;

    @FXML
    private AnchorPane userAnchorpane;

    @FXML
    private Pane userPane;

    @FXML
    private VBox userVBox;

    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private ListView<User> userListView;

    @FXML
    private Label infoLabel;

    public void initialize(){
        userListView.getItems().addAll(UserDAO.findAll());

        userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                infoLabel.setText("");
            }
        });
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void deleteUser(ActionEvent actionEvent) {
        User user = userListView.getSelectionModel().getSelectedItem();
        if (user != null){
            UserDAO.deleteUser(user);
            userListView.getItems().remove(user);
        }else{
            infoLabel.setText("zaznacz usera");
        }
    }

    public void addUser(ActionEvent actionEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if (!firstName.isEmpty() && !lastName.isEmpty()){
            UserDAO.addUser(firstName, lastName);
            userListView.getItems().clear();
            userListView.getItems().addAll(UserDAO.findAll());
        }else{
            infoLabel.setText("empty");
        }
    }
}


