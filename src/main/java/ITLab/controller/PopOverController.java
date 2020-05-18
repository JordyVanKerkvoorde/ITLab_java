package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.*;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import domain.MockData;
import domain.controllers.LocationController;
import domain.controllers.SessionController;
import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import domain.model.user.User;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.eclipse.persistence.jaxb.json.JsonSchemaOutputResolver;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class PopOverController implements Initializable {

    @FXML
    public JFXButton saveButton;
    @FXML
    private JFXEventTabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
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
    private JFXComboBox<User> responsible;
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
    @FXML
    private Label gastSprekerLabel;
    @FXML
    private JFXListView<User> ingeschreven;
    @FXML
    private JFXListView<User> aanwezig;
    @FXML
    private JFXListView<User> afwezig;
    @FXML
    private Label ingeschrevenLabel;
    @FXML
    private Label afwezigLabel;
    @FXML
    private Label afwezigLabelPercentage;
    @FXML
    private Label aanwezigLabel;

    public Session session;

    private CalendarController calendarController;

    public CalendarController getCalendarController() {
        return calendarController;
    }

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    public PopOverController() {

    }

    public void setSessionEntry() {
        session = tabPane.getSessionEntry().getUserObject();
        if (session == null) {
            session = new Session();
//            session.setSessionId(SessionController.getInstance().getSessions().size() + 1);
            tabPane.getSessionEntry().setUserObject(session);
            session.setTitle(tabPane.getSessionEntry().getTitle());
            session.setLocation(LocationController.getInstance().getLocations().get(0));
            session.setDescription("Nog geen beschrijving");
            session.setStartAndEnd(tabPane.getSessionEntry().getStartAsLocalDateTime(), tabPane.getSessionEntry().getEndAsLocalDateTime());
            System.out.println(session.getSessionId());
        }
        title.setText(session.getTitle());
        title.textProperty().addListener((observableValue, oldValue, newValue) -> {
            session.setTitle(newValue);
        });
        try {
            campus.getItems().addAll(Location.getLocationStrings());
            campus.getSelectionModel().select(session.getLocation().getCampus().toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        room.setText(session.getLocation().getLocationId());
        description.setText(session.getDescription());
        startDate.setValue(session.getStart().toLocalDate());
        startTime.set24HourView(true);
        startTime.setValue(session.getStart().toLocalTime());
        startTime.valueProperty().addListener((observableValue, localTime, t1) -> {
            session.setStartAndEnd(LocalDateTime.of(startDate.getValue(), startTime.getValue()), session.getEnd());
        });
        endDate.setValue(session.getEnd().toLocalDate());
        endTime.set24HourView(true);
        endTime.setValue(session.getEnd().toLocalTime());
        endTime.valueProperty().addListener((observableValue, localTime, t1) -> {
            session.setStartAndEnd(session.getStart(), LocalDateTime.of(endDate.getValue(), endTime.getValue()));
        });
        tabPane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/tabpane.css")).toExternalForm());
        tabPane.setPrefSize(550.0, 640.0);
        tab1.setText("Overzicht");
        tab2.setText("Aanwezigheden");
        tab3.setText("Bestanden");
        setCellFactory();
    }

    private void setListeners(Entry<Session> sessionEntry) {
        sessionEntry.titleProperty().addListener((observable, oldValue, newValue) -> sessionEntry.getUserObject().setTitle(newValue));
        sessionEntry.intervalProperty().addListener(((observable, oldValue, newValue) -> sessionEntry.getUserObject().setStartAndEnd(LocalDateTime.of(newValue.getStartDate(), newValue.getStartTime()),
                LocalDateTime.of(newValue.getEndDate(), newValue.getEndTime()))));
    }

    private void setCellFactory() {
        ingeschreven.getItems().clear();
        ingeschreven.setCellFactory(p -> new JFXListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getFirstName() == null) {
                    setText(null);
                } else {
                    setText(item.getFirstName() + " " + item.getLastName());
                }
                this.setOnMouseClicked(e -> System.out.println(item));
            }
        });
        ingeschreven.getItems().addAll(MockData.mockUsers);
    }

    private void setUpStyle() {
        List<Label> labels = new ArrayList<>(Arrays.asList(campusLabel, lokaalLabel, beginLabel, eindeLabel,
                verantwoordelijkLabel, campusLabel, lokaalLabel, gastSprekerLabel,
                ingeschrevenLabel, afwezigLabel, afwezigLabelPercentage, aanwezigLabel));
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
        saveButton.setOnAction((actionEvent) -> {
            SessionController.getInstance().updateSession(session);
            calendarController.loadSessions();
        });
    }

}
