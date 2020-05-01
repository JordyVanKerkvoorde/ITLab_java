package ITLab.controller;

import com.calendarfx.model.Entry;
import com.jfoenix.controls.*;
import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PopOverController implements Initializable {
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox<String> campus;
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
    @FXML
    private Label campusLabel;
    @FXML
    private Label lokaalLabel;
    @FXML
    private Label beginLabel;
    @FXML
    private Label eindeLabel;
    @FXML
    private Label verantwoordelijkLabel;

    public PopOverController() {

    }

    public void setSessionEntry(Entry<Session> sessionEntry) {

        title.setText(sessionEntry.getUserObject().getTitle());
        campus.getItems().addAll(Location.getLocationStrings());
        campus.getSelectionModel().select(sessionEntry.getUserObject().getLocation().getCampus().toString());
        room.setText(sessionEntry.getUserObject().getLocation().getLocationId());
        description.setText(sessionEntry.getUserObject().getDescription());
        startDate.setValue(sessionEntry.getStartDate());
        startTime.set24HourView(true);
        startTime.setValue(sessionEntry.getStartTime());
        endDate.setValue(sessionEntry.getEndDate());
        endTime.set24HourView(true);
        endTime.setValue(sessionEntry.getEndTime());
        //responsible.getItems().addAll();
        tabPane.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet/tabpane.css").toExternalForm());
        tabPane.setPrefSize(550.0, 640.0);
        tab1.setText("overzicht");
        tab2.setText("statistiek");
    }

    private void setUpStyle() {
        List<Label> labels = new ArrayList<>(Arrays.asList(campusLabel, lokaalLabel, beginLabel, eindeLabel, verantwoordelijkLabel));
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 12);
        labels.forEach(l -> l.setFont(font));
        title.setFont(font);
        description.setFont(font);
        Color c = Color.web("#455ca5");
        startDate.setDefaultColor(c);
        startTime.setDefaultColor(c);
        endDate.setDefaultColor(c);
        endTime.setDefaultColor(c);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpStyle();
    }
}
