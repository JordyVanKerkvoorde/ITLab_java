package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPopoverController implements Initializable {

    @FXML
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField firstnameField;
    @FXML
    private JFXComboBox<String> typeComboBox;
    @FXML
    private JFXComboBox<String> statusComboBox;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXButton commitButton;
    @FXML
    private JFXButton closeButton;

    private User user;

    private boolean isCreate;

    public UserPopoverController(User user, boolean isCreate) {
        this.user = user;
        this.isCreate = isCreate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isCreate) {
            commitButton.setText("Aanmaken");
            commitButton.setOnAction(event -> createUser(event));
        } else {
            commitButton.setText("Opslaan");
            commitButton.setOnAction(event -> saveUser(event));
        }
    }

    private void createUser(ActionEvent event) {

    }

    private void saveUser(ActionEvent event) {

    }


}
