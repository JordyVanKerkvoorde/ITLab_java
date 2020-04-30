package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import domain.MockData;
import domain.model.session.Announcement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AnnouncementsViewController implements Initializable {

    @FXML
    private JFXTextArea inputArea;
    @FXML
    private JFXButton commitButton;
    @FXML
    private JFXListView<Announcement> announcementsListView;

    private ObservableList<Announcement> announcementObservableList;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        announcementObservableList = FXCollections.observableArrayList();
        announcementObservableList.addAll(MockData.mockAnnouncements);

        announcementsListView.setItems(announcementObservableList);
        announcementsListView.setCellFactory(announcementListView -> new AnnouncementCell());
    }
}
