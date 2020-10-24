package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.model.Laptop;

import java.io.IOException;
import java.sql.*;

public class LaptopController {
    @FXML
    private AnchorPane laptopAnchorpane;

    @FXML
    private Pane userPane;

    @FXML
    private TableView<?> laptopTableView;

    @FXML
    private TableColumn<Laptop, Integer> laptopIdColumn;

    @FXML
    private TableColumn<Laptop, Integer> laptopPriceColumn;

    @FXML
    private TableColumn<Laptop, String> laptopNameColumn;

    @FXML
    private Button addLaptopButton;

    @FXML
    private Button deleteLaptopButton;

    @FXML
    private Button updateLaptopButton;

    @FXML
    private TextField laptopIdTextfield;

    @FXML
    private TextField laptopPriceTextfield;

    @FXML
    private TextField laptopNameTextfield;

    @FXML
    private Label laptopIdLabel;

    @FXML
    private Label laptopPriceLabel;

    @FXML
    private Label laptopNameLabel;


    private final String URL = "jdbc:mysql://localhost:3306/javafx_project?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "mysql";

    public void insertLaptopIntoTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO LAPTOP VALUES(null,?,?) ")) {
//            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(1, 200);
            preparedStatement.setString(2, "Dell");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public ObservableList<Laptop> getLaptopList() {
        ObservableList<Laptop> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM laptop";
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                list.addAll(new Laptop(resultSet.getInt("id"),
                        resultSet.getInt("PRICE"),
                        resultSet.getString("NAME")));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }


    public void showLaptopTable() {
        laptopIdColumn.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("id"));
        laptopPriceColumn.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("price"));
        laptopNameColumn.setCellValueFactory(new PropertyValueFactory<Laptop, String>("name"));
    }

    public void backButton(ActionEvent actionEvent) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            Scene scene = new Scene(parent);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }
}
