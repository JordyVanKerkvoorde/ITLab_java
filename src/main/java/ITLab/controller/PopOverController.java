package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.*;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import domain.MockData;
import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import domain.model.user.User;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.eclipse.persistence.jaxb.json.JsonSchemaOutputResolver;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class PopOverController implements Initializable {
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


    public PopOverController() {

    }

    public void setSessionEntry() {
        session = tabPane.getSessionEntry().getUserObject();
        Entry<Session> sessionEntry = tabPane.getSessionEntry();
        title.setText(session.getTitle());
        Bindings.bindBidirectional(sessionEntry.titleProperty(), title.textProperty());
        title.textProperty().addListener((observableValue, oldValue, newValue) -> session.setTitle(newValue));
        campus.getItems().addAll(Location.getLocationStrings());
        campus.getSelectionModel().select(session.getLocation().getCampus().toString());
        room.setText(session.getLocation().getLocationId());
        description.setText(session.getDescription());
        startDate.setValue(session.getStart().toLocalDate());
        startTime.set24HourView(true);
        startTime.setValue(session.getStart().toLocalTime());
        startTime.valueProperty().addListener((observableValue, localTime, t1) -> {
            sessionEntry.setInterval(t1, endTime.getValue());
            session.setStartAndEnd(LocalDateTime.of(sessionEntry.getStartDate(), sessionEntry.getStartTime()), session.getEnd());
        });
        endDate.setValue(session.getEnd().toLocalDate());
        endTime.set24HourView(true);
        endTime.setValue(session.getEnd().toLocalTime());
        endTime.valueProperty().addListener((observableValue, localTime, t1) -> {
            sessionEntry.setInterval(startTime.getValue(), t1);
            session.setStartAndEnd(session.getStart(), LocalDateTime.of(sessionEntry.getEndDate(), sessionEntry.getEndTime()));
        });
        tabPane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/tabpane.css")).toExternalForm());
        tabPane.setPrefSize(550.0, 640.0);
        tab1.setText("Overzicht");
        tab2.setText("Aanwezigheden");
        tab3.setText("Bestanden");
        setCellFactory();
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
    }
}
