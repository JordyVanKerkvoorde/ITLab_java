package ITLab.controller;

import ITLab.components.WindowButtons;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import domain.model.session.Announcement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class AnnouncementPopoverController implements Initializable {

    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXTextArea messageArea;
    @FXML
    private JFXButton mailButton;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane anchorPane;

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
        closeButton.setFont(getFont(closeButton.getFont().getSize()));
        saveButton.setFont(getFont(saveButton.getFont().getSize()));
        messageArea.setFont(getFont(messageArea.getFont().getSize() - 2));
        mailButton.setFont(getFont(mailButton.getFont().getSize()));
    }

    public Font getFont(double i) {
        return Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), i);
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
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Fout"));
            content.setBody(new Text("Aankondiging mag niet leeg zijn!\nGelieve een aankondiging in te vullen."));
            StackPane stackPane = new StackPane();
            stackPane.autosize();
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.LEFT, true);
            JFXButton button = new JFXButton("OK");
            button.setOnAction(event1 -> dialog.close());
            button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            button.setPrefHeight(32);
            content.setActions(button);
            anchorPane.getChildren().add(stackPane);
            AnchorPane.setTopAnchor(stackPane, (vbox.getHeight() - content.getPrefHeight()) / 2);
            AnchorPane.setLeftAnchor(stackPane, (vbox.getWidth() - content.getPrefWidth()) / 4);
            dialog.show();
            messageArea.setText(announcement.getMessage());
        }
    }
}
