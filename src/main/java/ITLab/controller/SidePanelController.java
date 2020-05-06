package ITLab.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import domain.MockData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SidePanelController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton b4;
    @FXML
    private JFXButton exit;

    private Callback callback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @FXML
    private void selectPanel(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Calendar": // calendar
                callback.loadCalendar();
                break;
            case "Announcements":
//                callback.unloadCalendar();
                callback.loadAnnouncements();
                break;
            case "Statistics":
//                MockData.mockSessions.forEach(e -> System.out.println(e));
                callback.loadStatistics();
                break;
            case "Users":
                callback.loadUsers();
                break;
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}