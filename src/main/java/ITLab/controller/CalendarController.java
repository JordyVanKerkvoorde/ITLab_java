package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import domain.MockData;
import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static com.calendarfx.model.CalendarEvent.ENTRY_CALENDAR_CHANGED;

public class CalendarController {
    private CalendarView calendarView;

    public CalendarController() {
        calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPageToolBarControls(false);
        Calendar sessionCalendar = new Calendar("Sessions");
        sessionCalendar.setStyle(Calendar.Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().add(sessionCalendar);
        for (Session session : MockData.mockSessions) {
            // id, title, start/end date
            Entry<Session> sessionEntry = new Entry<>();
            setListeners(session, sessionEntry);
            sessionCalendar.addEntry(sessionEntry);
        }
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("views/popover.fxml")));
            JFXEventTabPane popup = loader.load();
            PopOverController popOverController = loader.getController();
            popup.setPopOverController(popOverController);
            popup.parentProperty().addListener((observableValue, parent, t1) -> {
                if (parent == null) {
                    // TODO: write userObject to DB on change to null
                }
            });
            calendarView.setEntryDetailsPopOverContentCallback(param -> {
                Session session = (Session) param.getEntry().getUserObject();
                if (session.getDescription() == null) {
                    session.setDescription("No description set");
                    session.setLocation(new Location("no location set", CampusEnum.SCHOONMEERSEN, 1000));
                }
                return popup.setSession((Session) param.getEntry().getUserObject());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        sessionCalendar.addEventHandler(this::handelCalendarEvent);
        calendarView.getCalendarSources().clear();
        calendarView.getCalendarSources().add(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());
        setUpdateThread();
    }

    private void setListeners(Session session, Entry<Session> sessionEntry) {
        sessionEntry.setUserObject(session);
        sessionEntry.setTitle(session.getTitle());
        sessionEntry.setInterval(new Interval(session.getStart().toLocalDate(), session.getStart().toLocalTime(),
                session.getEnd().toLocalDate(), session.getEnd().toLocalTime()));
        sessionEntry.setLocation(session.getLocation().getCampus().name());
        sessionEntry.titleProperty().addListener((observable, oldValue, newValue) -> sessionEntry.getUserObject().setTitle(newValue));
        sessionEntry.intervalProperty().addListener(((observable, oldValue, newValue) -> sessionEntry.getUserObject().setStartAndEnd(LocalDateTime.of(newValue.getStartDate(), newValue.getStartTime()),
                LocalDateTime.of(newValue.getEndDate(), newValue.getEndTime()))));
    }

    private void handelCalendarEvent(CalendarEvent event) {
        if (event.getEventType() == ENTRY_CALENDAR_CHANGED && event.isEntryAdded()) {
            try {
                @SuppressWarnings("unchecked") Entry<Session> entry = (Entry<Session>) event.getEntry();
                Session session = new Session();
                entry.setUserObject(session);
                session.setTitle(entry.getTitle());
                Location location = new Location();
                session.setLocation(location);
                session.setStartAndEnd(LocalDateTime.of(entry.getStartDate(), entry.getStartTime()),
                        LocalDateTime.of(entry.getEndDate(), entry.getEndTime()));
                MockData.mockSessions.add(session);
                System.out.println(session);
            } catch (Exception e) {
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

    public CalendarView getCalendarView() {
        return calendarView;
    }
}
