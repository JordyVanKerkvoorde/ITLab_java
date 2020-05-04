package ITLab.controller;

import ITLab.components.UserCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import domain.MockData;
import domain.model.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
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

        setDoubleClickHandler();
        setAddUserHandler();
    }

    private void setDoubleClickHandler() {
        userTableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
                    User user = row.getItem();
                    loadPopover(user);
                }
            });
            return row;
        });
    }

    private void setAddUserHandler() {
        addUserButton.setOnAction(event -> loadPopover(null));
    }

    private void loadPopover(User user) {
        boolean isCreate = user == null;
        Parent root;
        try {
            UserPopoverController controller = new UserPopoverController(user, isCreate, userObservableList);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/userpopover.fxml"));
            loader.setController(controller);
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gebruiker");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
            refreshUserTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void refreshUserTable() {
        userTableView.getItems().clear();
        userTableView.getItems().addAll(userObservableList);
        userTableView.sort();
    }
}
