package ITLab.controller;

import ITLab.components.UserCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.MockData;
import domain.model.user.User;
import domain.model.user.UserStatus;
import domain.model.user.UserType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
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
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField firstnameField;
    @FXML
    private JFXComboBox<UserType> typeComboBox;
    @FXML
    private JFXComboBox<UserStatus> statusComboBox;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXButton commitButton;
    @FXML
    private VBox vBox2;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private Label errorMessage;
    @FXML
    private Label titleLbl;
    @FXML
    private AnchorPane region;
    @FXML
    private AnchorPane region2;
    @FXML
    private JFXTextField searchTxf;
    @FXML
    private HBox titleBar;
    @FXML
    private HBox searchBar;

    private ObservableList<User> userObservableList;
    private User selectedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(MockData.mockUsers);

        setUpSearchField();

        setColumnFactories();
        setColumnWidth();

//        userTableView.getItems().addAll(userObservableList);
//        userTableView.getSortOrder().add(familieNaamColumn);

        selectedUserChangedHandler();
        setAddUserHandler();
        vBox.setVisible(false);
        errorMessage.setVisible(false);
        HBox.setHgrow(vBox2, Priority.ALWAYS);
        VBox.setVgrow(hBox, Priority.ALWAYS);
        VBox.setVgrow(userTableView, Priority.ALWAYS);
        setStyle();
    }

    private Font getFont(double i){
        return Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), i);
    }

    private void setStyle() {
        anchorpane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/announcementsview.css")).toExternalForm());
        titleLbl.setFont(getFont(50));
        region2.prefHeightProperty().bind(titleBar.heightProperty());
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);
        searchBar.prefWidthProperty().bind(userTableView.widthProperty());


        //announcementsListView.prefHeightProperty().bindBidirectional(anchorPane.prefHeightProperty());
    }

    private void selectedUserChangedHandler(){
        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User currentUser) {
                errorMessage.setVisible(false);
                if(currentUser == null){
                    currentUser = user;
                }
                selectedUser = currentUser;
                vBox.setVisible(true);
                initializeUserPanel();

            }
        });
    }

    /**
     * Sets the action of the add button
     * Loads a AddUserPopover when clicked
     */
    private void setAddUserHandler() {
        addUserButton.setOnAction(event ->{
            selectedUser = new User();
            initializeUserPanel();
            vBox.setVisible(true);
        });
    }

    private void setUpSearchField() {
        FilteredList<User> filteredUsers = new FilteredList<>(userObservableList, b -> true);
        searchTxf.textProperty().addListener((observable, oldValue, newValue) -> {
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

    /**
     * refreshes the tableView after edits
     */
//    private void refreshUserTable() {
////        userTableView.getItems().clear();
////        userTableView.getItems().addAll(userObservableList);
////        userTableView.sort();
//        userTableView.refresh();
//    }

    private void initializeUserPanel(){
        initializeComboBoxes();
        //closeButton.setOnAction(event -> close(event));
        //     * @param user: if user is null -> edit user popover
        //     *              if user not null -> create user popover

            commitButton.setText("Opslaan");
            commitButton.setOnAction(event -> saveUser());
            fillUserData();

    }

    public void initializeComboBoxes() {
        statusComboBox.setItems(FXCollections.observableArrayList(UserStatus.values()));
        typeComboBox.setItems(FXCollections.observableArrayList(UserType.values()));
    }

    private void fillUserData() {
        lastnameField.setText(selectedUser.getLastName());
        firstnameField.setText(selectedUser.getFirstName());
        typeComboBox.setValue(selectedUser.getUserType());
        statusComboBox.setValue(selectedUser.getUserStatus());
        usernameField.setText(selectedUser.getUserName());
    }

    private void saveUser() {
        try{
            selectedUser.setLastName(lastnameField.getText());
            selectedUser.setFirstName(firstnameField.getText());
            selectedUser.setUserName(usernameField.getText());
            selectedUser.setUserStatus(statusComboBox.getValue());
            selectedUser.setUserType(typeComboBox.getValue());
            if(!userObservableList.contains(selectedUser)){
                userObservableList.add(selectedUser);
                MockData.mockUsers.add(selectedUser);
            }
            userTableView.refresh();
        }catch(IllegalArgumentException e){
            errorMessage.setVisible(true);
            errorMessage.setText("De velden mogen niet leeg zijn!");
        }
    }
}
