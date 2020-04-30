package ITLab.controller;

import com.jfoenix.controls.JFXListCell;
import domain.model.session.Announcement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AnnouncementCell extends JFXListCell<Announcement> {

    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Announcement item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
//            if (loader == null) {
//                loader = new FXMLLoader(getClass().getResource("views/announcementcell.fxml"));
////                loader.setController(this);
//
//                try {
//                    loader.load();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            setText(item.getMessage());
//            messageLabel.setText(item.getMessage());
//            getChildren().add(new Label("hello"));
            setOnMouseClicked(ev -> {
                setText("clicked");
                item.setMessage("Ik ben ooit op geklikt geweest");
            });
//            setText(null);

        }
    }
}
