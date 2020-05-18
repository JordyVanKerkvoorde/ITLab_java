package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXDrawer;
import domain.MockData;
import domain.controllers.AnnouncementController;
import domain.model.session.Location;
import domain.model.session.Session;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.calendarfx.model.CalendarEvent.ENTRY_CALENDAR_CHANGED;

public class MainController implements Initializable, Callback {


    @FXML
    private AnchorPane root;

    @FXML
    private StackPane body;

    private CalendarController calendarController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bindings.bindBidirectional(root.prefHeightProperty(), body.prefHeightProperty());
        Bindings.bindBidirectional(root.prefWidthProperty(), body.prefWidthProperty());
        calendarController = new CalendarController();
        loadCalendar();
        loadSidepanel();
        loadCalendar();
    }

    @Override
    public void loadAnnouncements() {
        //TODO: add check if announcements are already loaded
        body.getChildren().clear();
        try {
            System.out.println("calling announcements");
            FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("views/announcementsview.fxml")));
            AnchorPane anchorPane = loader.load();
            AnnouncementsViewController controller = loader.getController();
            body.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadStatistics() {
        //TODO: add check if announcements are already loaded
        body.getChildren().clear();
        try {
            System.out.println("calling announcements");
            FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("views/statisticsview.fxml")));
            AnchorPane anchorPane = loader.load();
            StatisticsViewController controller = loader.getController();
            controller.setStackPane(body);
            body.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadUsers() {
        body.getChildren().clear();
        try {
            System.out.println("calling announcements");
            FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("views/usersview.fxml")));
            AnchorPane anchorPane = loader.load();
            UsersViewController controller = loader.getController();
            body.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadCalendar() {
        body.getChildren().clear();
        body.getChildren().add(calendarController.getCalendarView());

    }

    private void loadSidepanel() {
        try {
            System.out.println("calling sidepanel");
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setCallback(this);
            box.setMaxWidth(226.0);
            root.getChildren().add(box);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
