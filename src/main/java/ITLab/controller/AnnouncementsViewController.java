package ITLab.controller;

import ITLab.components.AnnouncementCell;
import com.jfoenix.controls.*;
import domain.MockData;
import domain.model.session.Announcement;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnnouncementsViewController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private JFXTextArea inputArea;
    @FXML
    private JFXButton commitButton;
    @FXML
    private JFXListView<Announcement> announcementsListView;

    @FXML
    private AnchorPane anchorPane;

    private ObservableList<Announcement> announcementObservableList;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        announcementObservableList = FXCollections.observableArrayList();
        announcementObservableList.addAll(MockData.mockAnnouncements);
//        announcementObservableList.addListener(new ListChangeListener<Announcement>() {
//            @Override
//            public void onChanged(Change<? extends Announcement> change) {
//                MockData.mockAnnouncements = announcementObservableList;
//            }
//        });
        announcementsListView.setItems(announcementObservableList);
        announcementsListView.setCellFactory(announcementListView -> new AnnouncementCell());
        commitButton.setOnAction(event -> createAnnouncement(event));
        setStyle();
    }

    private void setStyle() {
        anchorPane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/announcementsview.css")).toExternalForm());
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 12);
        inputArea.setFont(font);
        title.setFont(font);
        //announcementsListView.prefHeightProperty().bindBidirectional(anchorPane.prefHeightProperty());
    }

    private void createAnnouncement(ActionEvent event) {
        try {
            Announcement announcement = new Announcement(inputArea.getText());
            announcementObservableList.add(0, announcement);
            inputArea.clear();
        } catch (IllegalArgumentException e) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error"));
            content.setBody(new Text("Aankondiging mag niet leeg zijn!\nGelieve een aankondiging in te vullen."));
            StackPane stackPane = new StackPane();
            stackPane.autosize();
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.LEFT, true);
            JFXButton button = new JFXButton("Okay");
            button.setOnAction(event1 -> dialog.close());
            button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            button.setPrefHeight(32);
            content.setActions(button);
            anchorPane.getChildren().add(stackPane);
            AnchorPane.setTopAnchor(stackPane, (inputArea.getHeight() - content.getPrefHeight()) / 2);
            AnchorPane.setLeftAnchor(stackPane, (inputArea.getWidth() - content.getPrefWidth()) / 2);
            dialog.show();
        }
    }
}
