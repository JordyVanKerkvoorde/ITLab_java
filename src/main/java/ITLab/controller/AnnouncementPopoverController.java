package ITLab.controller;

import com.jfoenix.controls.*;
import domain.MockData;
import domain.controllers.AnnouncementController;
import domain.model.Mail;
import domain.model.session.Announcement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    @FXML
    private JFXButton mailButton;
    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label notification;

    private Announcement announcement;

    public AnnouncementPopoverController(Announcement announcement) {
        this.announcement = announcement;
    }

    private static AnnouncementController announcementController = AnnouncementController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeButton.setOnAction(this::close);
        saveButton.setOnAction(this::save);
        messageArea.setText(announcement.getMessage());
        closeButton.setFont(getFont(closeButton.getFont().getSize()));
        saveButton.setFont(getFont(saveButton.getFont().getSize()));
        messageArea.setFont(getFont(messageArea.getFont().getSize() - 2));
        mailButton.setFont(getFont(mailButton.getFont().getSize()));
        checkBox.setDisable(true);
        checkBox.setSelected(announcement.isMailed());
        setMailButton();
        notification.setVisible(false);

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
            announcement.setMailed(false);
            announcementController.updateAnnouncement(announcement);
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

    private void setMailButton(){
        mailButton.setOnMouseClicked(e -> {
            notification.setVisible(true);
            mailButton.setDisable(true);
            Mail mail = new Mail();
            mail.setupMail("ITLab aankondiging", announcement.getMessage(), MockData.mockUsers);
            Thread t = new Thread(mail);
            t.start();
            Thread t2 = new Thread(() -> {
                while(t.isAlive()){}
                checkBox.setSelected(true);
                announcement.setMailed(true);
                mailButton.setDisable(false);
                notification.setVisible(false);
            });
            t2.start();
        });
    }
}
