package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.Entry;
import com.jfoenix.controls.*;
import domain.MockData;
import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import domain.model.user.User;
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

import java.net.URL;
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
    public Session sessionEntry;

    public PopOverController() {

    }

    public void setSessionEntry() {
        sessionEntry = tabPane.getSession();
        title.setText(sessionEntry.getTitle());
        campus.getItems().addAll(Location.getLocationStrings());
        campus.getSelectionModel().select(sessionEntry.getLocation().getCampus().toString());
        room.setText(sessionEntry.getLocation().getLocationId());
        description.setText(sessionEntry.getDescription());
        startDate.setValue(sessionEntry.getStart().toLocalDate());
        startTime.set24HourView(true);
        startTime.setValue(sessionEntry.getStart().toLocalTime());
        endDate.setValue(sessionEntry.getEnd().toLocalDate());
        endTime.set24HourView(true);
        endTime.setValue(sessionEntry.getEnd().toLocalTime());
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
