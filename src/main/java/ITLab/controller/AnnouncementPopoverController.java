package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import domain.model.session.Announcement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AnnouncementPopoverController implements Initializable {

    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXTextArea messageArea;

    private Announcement announcement;

    public AnnouncementPopoverController(Announcement announcement) {
        this.announcement = announcement;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeButton.setOnAction(event -> close(event));
        saveButton.setOnAction(event -> save(event));
        messageArea.setText(announcement.getMessage());
        System.out.println(announcement.getMessage());
    }

    public void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent event) {
        try {
            announcement.setMessage(messageArea.getText());
            close(event);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aankondiging mag niet leeg zijn!");
            alert.setContentText("Gelieve een aankondiging in te vullen.");
            alert.showAndWait();
            messageArea.setText(announcement.getMessage());
        }
    }
}
