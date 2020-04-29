package ITLab.controller;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import domain.model.session.Location;
import domain.MockData;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.calendarfx.model.CalendarEvent.ENTRY_CALENDAR_CHANGED;

public class MainController implements Initializable, Callback {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane body;

    private CalendarView calendarView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bindings.bindBidirectional(root.prefHeightProperty(), body.prefHeightProperty());
        Bindings.bindBidirectional(root.prefWidthProperty(), body.prefWidthProperty());
        setupCalendar();
        loadSidepanel();
        drawer.open();
        loadCalendar();
    }


    private void setupCalendar() {
        calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPageToolBarControls(false);
        Calendar sessionCalendar = new Calendar("Sessions");
        sessionCalendar.setStyle(Calendar.Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().add(sessionCalendar);

        for (Session session: MockData.mockSessions) {
            // id, title, start/end date
            SessionEntry<Session> sessionEntry = new SessionEntry<>();
            sessionEntry.setUserObject(session);
            sessionEntry.setTitle(session.getTitle());
            sessionEntry.setInterval(new Interval(session.getStart().toLocalDate(), session.getStart().toLocalTime(),
                    session.getEnd().toLocalDate(), session.getEnd().toLocalTime()));
            sessionEntry.setLocation(session.getLocation().getCampus().name());
            sessionEntry.titleProperty().addListener((observable, oldValue, newValue) -> {
                sessionEntry.getUserObject().setTitle(newValue);
            });
            sessionEntry.intervalProperty().addListener(((observable, oldValue, newValue) -> {
                sessionEntry.getUserObject().setStartAndEnd(LocalDateTime.of(newValue.getStartDate(), newValue.getStartTime()),
                        LocalDateTime.of(newValue.getEndDate(), newValue.getEndTime()));
//                sessionEntry.getUserObject().setStart(LocalDateTime.of(newValue.getStartDate(), newValue.getStartTime()));
//                sessionEntry.getUserObject().setEnd(LocalDateTime.of(newValue.getEndDate(), newValue.getEndTime()));
            }));
            System.out.println(session.getDescription());
            //data toevoegen aan sessionEntry
            sessionEntry.setDescription(session.getDescription());
            //sessionEntry adden aan cal
            sessionCalendar.addEntry(sessionEntry);
            //nieuwe popover
            calendarView.setEntryDetailsPopOverContentCallback(param -> new PopOverController(sessionEntry));

        }
        sessionCalendar.addEventHandler(event -> handelCalendarEvent(event));
        calendarView.getCalendarSources().clear();
        calendarView.getCalendarSources().add(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());
        setUpdateThread();
    }

    private void handelCalendarEvent(CalendarEvent event) {
        if(event.getEventType() == ENTRY_CALENDAR_CHANGED && event.isEntryAdded()){
            try{
                // this method gets called when a new event is created in the calendar
                // a new Session has to be created and listeners have to be added to the new
                // Entry<Session> that updates the Session object that has to be in the db
                // session.sessionId won't be set because that has to happen in db
                Entry entry = event.getEntry();
                Session session = new Session();
                entry.setUserObject(session);
                session.setTitle(entry.getTitle());
                Location location = new Location();
                session.setLocation(location);
                session.setStartAndEnd(LocalDateTime.of(entry.getStartDate(), entry.getStartTime()),
                        LocalDateTime.of(entry.getEndDate(), entry.getEndTime()));
//                session.setStart(LocalDateTime.of(entry.getStartDate(), entry.getStartTime()));
//                session.setEnd(LocalDateTime.of(entry.getEndDate(), entry.getEndTime()));
                MockData.mockSessions.add(session);
                System.out.println(session);
            }catch (Exception e ){
                System.out.println(e);
            }
        }
    }

    private void setUpdateThread() {
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    public void loadSession(Session session) {
        try{
            body.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/session.fxml"));
            ScrollPane sessionPane = loader.load();
            SessionController sessionController = loader.getController();
            sessionController.setSession(session);
            body.getChildren().add(sessionPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCalendar() {
        if(!body.getChildren().contains(calendarView)){
            body.getChildren().add(calendarView);
        }

    }

    public void unloadCalendar(){
        if(!body.getChildren().contains(calendarView)){
            body.getChildren().remove(calendarView);
        }
    }
    private void loadSidepanel() {
        try {
            System.out.println("calling sidepanel");
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setCallback(this);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
