package ITLab.controller;

import com.jfoenix.controls.*;
import domain.model.session.CampusEnum;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class PopOverController extends Node implements Initializable {
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox campus;
    @FXML
    private JFXTextField room;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXTimePicker endTime;
    @FXML
    private JFXComboBox responsible;

    public PopOverController(SessionEntry<Session> sessionEntry) {
        title.setText(sessionEntry.getTitle());
        campus.getItems().addAll(CampusEnum.values());
        campus.getSelectionModel().select(sessionEntry.getSessionLocation().getCampus());
        room.setText(sessionEntry.getSessionLocation().getLocationId());
        description.setText(sessionEntry.getDescription());
        startDate.setValue(sessionEntry.getStartDate());
        startTime.set24HourView(true);
        startTime.setValue(sessionEntry.getStartTime());
        endDate.setValue(sessionEntry.getEndDate());
        endTime.set24HourView(true);
        endTime.setValue(sessionEntry.getEndTime());
        //responsible.getItems().addAll();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
