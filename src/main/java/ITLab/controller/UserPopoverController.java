package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domain.controllers.UserController;
import domain.model.user.User;
import domain.model.user.UserStatus;
import domain.model.user.UserType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class UserPopoverController implements Initializable {
    @FXML
    private VBox popOver;

    @FXML
    private JFXTextField lastnameField;
    @FXML
    private JFXTextField firstnameField;
    @FXML
    private JFXComboBox<UserType> typeComboBox;
    @FXML
    private JFXComboBox<UserStatus> statusComboBox;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXButton commitButton;
    @FXML
    private JFXButton closeButton;
    @FXML
    private Label familienaam;
    @FXML
    private Label voornaam;
    @FXML
    private Label email;
    @FXML
    private Label status;
    @FXML
    private Label type;

    private User user;

    private static UserController userController = UserController.getInstance();


    public UserPopoverController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        closeButton.setOnAction(event -> close(event));
        if (user == null) {
            user =  new User();
            commitButton.setText("Aanmaken");
            commitButton.setOnAction(event -> saveUser(event));
        } else {
            commitButton.setText("Opslaan");
            commitButton.setOnAction(event -> saveUser(event));
            fillUserData();
        }
        setStyle();
    }

    public void initializeComboBoxes() {
        statusComboBox.setItems(FXCollections.observableArrayList(UserStatus.values()));
        typeComboBox.setItems(FXCollections.observableArrayList(UserType.values()));
    }

    private void saveUser(ActionEvent event) {
        user.setEmailConfirmed(true);
        user.setLastName(lastnameField.getText());
        user.setFirstName(firstnameField.getText());
        user.setUserName(emailField.getText());
        user.setEmail(emailField.getText());
        user.setUserStatus(statusComboBox.getValue());
        user.setUserType(typeComboBox.getValue());
//        if(!MockData.mockUsers.contains(user)){
//            MockData.mockUsers.add(user);
//        }
        if (!userController.getUsers().contains(user)) {
            user.setId(Integer.toString(emailField.getText().hashCode()));
            userController.addUser(user);
        } else {
            userController.updateUser(user);
        }
        close(event);
    }

    private void fillUserData() {
        lastnameField.setText(user.getLastName());
        firstnameField.setText(user.getFirstName());
        typeComboBox.setValue(user.getUserType());
        statusComboBox.setValue(user.getUserStatus());
        emailField.setText(user.getUserName());
    }

    private void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private Font getFont(double i){
        return Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), i);
    }

    private void setStyle() {
        popOver.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/announcementsview.css")).toExternalForm());
        //titleLbl.setFont(getFont(50));
        List<Label> labels = new ArrayList<>(Arrays.asList(familienaam, voornaam, email, status, type));
        labels.forEach(l -> l.setFont(getFont(12)));
    }
}