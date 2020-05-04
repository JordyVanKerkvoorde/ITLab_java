package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.MockData;
import domain.model.user.User;
import domain.model.user.UserStatus;
import domain.model.user.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPopoverController implements Initializable {

    private ObservableList<User> userObservableList;
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
    private JFXButton closeButton;

    private User user;

    private boolean isCreate;

    public UserPopoverController(User user, boolean isCreate, ObservableList<User> userObservableList) {
        this.user = user;
        this.isCreate = isCreate;
        this.userObservableList = userObservableList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        closeButton.setOnAction(event -> close(event));
        if (isCreate) {
            commitButton.setText("Aanmaken");
            commitButton.setOnAction(event -> createUser(event));
        } else {
            commitButton.setText("Opslaan");
            commitButton.setOnAction(event -> saveUser(event));
            fillUserData();
        }
    }

    public void initializeComboBoxes() {
        statusComboBox.setItems(FXCollections.observableArrayList(UserStatus.values()));
        typeComboBox.setItems(FXCollections.observableArrayList(UserType.values()));
    }

    private void createUser(ActionEvent event) {
        User user = new User();
        user.setLastName(lastnameField.getText());
        user.setFirstName(firstnameField.getText());
        user.setUserName(usernameField.getText());
        user.setUserStatus(statusComboBox.getValue());
        user.setUserType(typeComboBox.getValue());
        user.setPenalties(0);
        user.setUserId(firstnameField.getText().toLowerCase() + lastnameField.getText().toLowerCase());
        user.setEmail(usernameField.getText());

        //TODO: databank
        userObservableList.add(user);
        MockData.mockUsers.add(user);
        close(event);
    }

    private void saveUser(ActionEvent event) {
        user.setLastName(lastnameField.getText());
        user.setFirstName(firstnameField.getText());
        user.setUserName(usernameField.getText());
        user.setUserStatus(statusComboBox.getValue());
        user.setUserType(typeComboBox.getValue());
        close(event);
    }

    private void fillUserData() {
        lastnameField.setText(user.getLastName());
        firstnameField.setText(user.getFirstName());
        typeComboBox.setValue(user.getUserType());
        statusComboBox.setValue(user.getUserStatus());
        usernameField.setText(user.getUserName());
    }

    private void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
