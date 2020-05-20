package ITLab.controller;

import ITLab.components.JFXEventTabPane;
import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import domain.controllers.SessionController;
import domain.model.session.Session;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class CalendarController {
    private CalendarView calendarView;
    private Calendar sessionCalendar;
    private CalendarSource myCalendarSource;

    public CalendarController() {
        // creating the view
        calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPageToolBarControls(false);
        sessionCalendar = new Calendar("Sessions");
        sessionCalendar.setStyle(Calendar.Style.STYLE2);
        // setting the source
        myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().add(sessionCalendar);
        for (Session session : SessionController.getInstance().getSessions()) {
            // id, title, start/end date
            Entry<Session> sessionEntry = new Entry<>();
            setListeners(session, sessionEntry);
            sessionCalendar.addEntry(sessionEntry);
        }


        // creating the popover scene
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("views/popover.fxml")));
            JFXEventTabPane popup = loader.load();
            PopOverController popOverController = loader.getController();
            popOverController.setCalendarController(this);
            // setting the controller of the popup
            popup.setPopOverController(popOverController);
            // if popover loses parent => it's closed save to db
            popup.focusedProperty().addListener((observable) -> {
                // update or add to db
                Session session = popup.getSessionEntry().getUserObject();
                //SessionController.getInstance().updateSession(session);
            });
            // set scene visible when entry is clicked
            calendarView.setEntryDetailsPopOverContentCallback(param -> popup.setSession((Entry<Session>) param.getEntry()));

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void loadSessions() {
        sessionCalendar.clear();
        for (Session session : SessionController.getInstance().getSessions()) {
            System.out.println(session);
            // id, title, start/end date
            Entry<Session> sessionEntry = new Entry<>();
            //System.out.println(s);
            setListeners(session, sessionEntry);
            sessionEntry.setUserObject(session);
            sessionCalendar.addEntry(sessionEntry);

        }
    }

    private void handelCalendarEvent(CalendarEvent event) {
//        if (event.getEventType() == ENTRY_CALENDAR_CHANGED && event.isEntryAdded()) {
//            try {
//                // this method gets called when a new event is created in the calendar
//                // a new Session has to be created and listeners have to be added to the new
//                // Entry<Session> that updates the Session object that has to be in the db
//                // session.sessionId won't be set because that has to happen in db
//                @SuppressWarnings("unchecked") Entry<Session> entry = (Entry<Session>) event.getEntry();
//                Session session = new Session();
//                entry.setUserObject(session);
//                session.setTitle(entry.getTitle());
//                Location location = new Location();
//                session.setLocation(location);
//                session.setStartAndEnd(LocalDateTime.of(entry.getStartDate(), entry.getStartTime()),
//                        LocalDateTime.of(entry.getEndDate(), entry.getEndTime()));
////                session.setStart(LocalDateTime.of(entry.getStartDate(), entry.getStartTime()));
////                session.setEnd(LocalDateTime.of(entry.getEndDate(), entry.getEndTime()));
//                MockData.mockSessions.add(session);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
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
