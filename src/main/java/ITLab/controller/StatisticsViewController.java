package ITLab.controller;

import com.jfoenix.controls.JFXMasonryPane;
import domain.model.session.Session;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsViewController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label title;

    private StackPane stackPane;

    private Session session;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setSession(Session session) {
        title.setText(session.getTitle());
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
        Bindings.bindBidirectional(anchorPane.prefHeightProperty(), stackPane.prefHeightProperty());
        Bindings.bindBidirectional(anchorPane.prefWidthProperty(), stackPane.prefWidthProperty());
        Bindings.bindBidirectional(anchorPane.prefHeightProperty(), vbox.prefHeightProperty());
        Bindings.bindBidirectional(anchorPane.prefWidthProperty(), vbox.prefWidthProperty());
        System.out.println(stackPane.getWidth() + " " + stackPane.getHeight());
    }

}
