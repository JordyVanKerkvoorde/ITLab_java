package ITLab.components;

import ITLab.controller.AnnouncementPopoverController;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextArea;
import domain.model.session.Announcement;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class AnnouncementCell extends JFXListCell<Announcement> {

    @FXML
    private Label messageLabel;
    @FXML
    private HBox hbox;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Announcement item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/views/announcementcell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Bindings.bindBidirectional(minHeightProperty(), hbox.prefHeightProperty());
            setCellDoubleClickedHandler(item);
            fillCell(item);

        }
    }

    private void setCellDoubleClickedHandler(Announcement announcement) {
        setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Parent root;
                try {
                    AnnouncementPopoverController controller = new AnnouncementPopoverController(announcement);
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/announcementpopover.fxml"));
                    loader.setController(controller);
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Aankondiging aanpassen");
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.showAndWait();
                    updateItem(announcement, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fillCell(Announcement item) {
        messageLabel.setText(item.getMessage());
        messageLabel.setMaxWidth(400);
        messageLabel.setWrapText(true);
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 12);
        messageLabel.setFont(font);
        setText(null);
        setGraphic(hbox);
    }
}
