package ITLab.controller;

import ITLab.components.UserCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import domain.MockData;
import domain.model.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {

    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> familieNaamColumn;
    @FXML
    private TableColumn<User, String> voornaamColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> typeColumn;
    @FXML
    private TableColumn<User, String> statusColumn;
    @FXML
    private JFXButton addUserButton;

    private ObservableList<User> userObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(MockData.mockUsers);

        setColumnFactories();
        setColumnWidth();

        userTableView.getItems().addAll(userObservableList);
        userTableView.getSortOrder().add(familieNaamColumn);
    }

    private void setColumnWidth() {
        familieNaamColumn.prefWidthProperty().bind(userTableView.widthProperty().multiply(0.2));
        voornaamColumn.prefWidthProperty().bind(userTableView.widthProperty().multiply(0.2));
        userNameColumn.prefWidthProperty().bind(userTableView.widthProperty().multiply(0.395));
        typeColumn.prefWidthProperty().bind(userTableView.widthProperty().multiply(0.1));
        statusColumn.prefWidthProperty().bind(userTableView.widthProperty().multiply(0.1));
    }

    private void setColumnFactories() {
        familieNaamColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        voornaamColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("userStatus"));
    }
}
