package sample.controller;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.awt.print.Pageable;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.internal.scene.control.skin.agenda.AgendaMonthSkin;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import sample.Main;

public class MainController implements Initializable, Callback {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    @FXML
    private HBox body;

    private AnchorPane agendaPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Main.isSplashLoaded) {
            loadSplashScreen();
        }

        try {
            setupCalendar();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setCallback(this);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        var transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        drawer.open();
    }
    private void setupCalendar() {

        try {
            FXMLLoader agenda = new FXMLLoader(getClass().getClassLoader().getResource("views/calendar_main.fxml"));
            URL resource = getClass().getClassLoader().getResource("stylesheet/stylesheet_main.fxml");
            root.getStylesheets().add(String.valueOf(resource));
            agendaPane = agenda.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public void updateColor(String newColor) {
        root.setStyle("-fx-background-color:" + newColor);
    }


    @Override
    public void loadCalendar() {
        if(!body.getChildren().contains(agendaPane)){
            body.getChildren().add(agendaPane);
        }

    }
    public void unloadCalendar(){
        try{
            body.getChildren().remove(agendaPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
