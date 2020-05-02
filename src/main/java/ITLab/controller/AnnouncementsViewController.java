package ITLab.controller;

import ITLab.components.AnnouncementCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import domain.MockData;
import domain.model.session.Announcement;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AnnouncementsViewController implements Initializable {

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
    }

    private void createAnnouncement(ActionEvent event) {
        try {
            Announcement announcement = new Announcement(inputArea.getText());
            announcementObservableList.add(0, announcement);
            inputArea.clear();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aankondiging mag niet leeg zijn!");
            alert.setContentText("Gelieve een aankondiging in te vullen.");
            alert.showAndWait();
        }
    }
}
