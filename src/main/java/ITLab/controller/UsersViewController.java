package ITLab.controller;

import ITLab.components.UserCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.MockData;
import domain.model.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Label titleLbl;
    @FXML
    private AnchorPane spacing1;
    @FXML
    private AnchorPane spacing2;

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
    @FXML
    private JFXTextField searchTx;

    private ObservableList<User> userObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(MockData.mockUsers);

        setColumnFactories();
        setColumnWidth();
        userTableView.getItems().addAll(userObservableList);
        userTableView.getSortOrder().add(familieNaamColumn);
        setUpSearchField();
        setAddUserButton();
        setDoubleClickHandler();
        setStyle();
    }

    private void setAddUserButton(){
        addUserButton.setOnMouseClicked( e -> {
            loadPopOver((null));
        });
    }
    private void setDoubleClickHandler() {
        userTableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
                    User user = row.getItem();
                    loadPopOver(user);
                }
            });
            return row;
        });
    }

    private void loadPopOver(User user) {
        Parent root;
        try {
            UserPopoverController controller = new UserPopoverController(user);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/userpopover.fxml"));
            loader.setController(controller);
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gebruiker aanpassen");
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            updateTableView();
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

    private void setUpSearchField() {
        FilteredList<User> filteredUsers = new FilteredList<>(userObservableList, b -> true);
        searchTx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredUsers.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getUserName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<User> sortedUsers = new SortedList<>(filteredUsers);
        sortedUsers.comparatorProperty().bind(userTableView.comparatorProperty());
        userTableView.setItems(sortedUsers);
    }

    private Font getFont(double i){
        return Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), i);
    }

    private void setStyle() {
        anchorpane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/announcementsview.css")).toExternalForm());
        titleLbl.setFont(getFont(50));
        HBox.setHgrow(spacing1, Priority.ALWAYS);
        HBox.setHgrow(spacing2, Priority.ALWAYS);
    }

    private void updateTableView(){
        userObservableList.clear();
        userObservableList.addAll(MockData.mockUsers);
        userTableView.refresh();
    }
}