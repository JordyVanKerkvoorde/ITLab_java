package ITLab.controller;

import ITLab.Main;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import domain.Session;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        if (!Main.isSplashLoaded) {
//            loadSplashScreen();
//        }
        root.setBorder(new Border(new BorderStroke(Color.BLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        body.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Bindings.bindBidirectional(root.prefHeightProperty(), body.prefHeightProperty());
        Bindings.bindBidirectional(root.prefWidthProperty(), body.prefWidthProperty());
        setupCalendar();
        loadSidepanel();
        drawer.open();
        loadCalendar();
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


    private void setupCalendar() {
        calendarView = new CalendarView();
        Calendar birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("holidays");
        birthdays.setStyle(Calendar.Style.STYLE1);
        holidays.setStyle(Calendar.Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays, holidays);

        calendarView.getCalendarSources().addAll(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());
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

            ;
        };
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    // useless splashscreen into code
    private void loadSplashScreen() {
        try {
            Main.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/main.fxml")));
                    root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void loadCalendarFX() {
        try{
            body.getChildren().clear();
            body.getChildren().add(calendarView);


        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void loadCalendar() {
        if(!body.getChildren().contains(calendarView)){
            body.getChildren().add(calendarView);
        }

    }
    public void unloadCalendar(){
        try{
            body.getChildren().remove(calendarView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
