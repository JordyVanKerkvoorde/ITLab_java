package ITLab.components;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextArea;
import domain.model.session.Announcement;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class AnnouncementCell extends JFXListCell<Announcement> {

    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private HBox hbox;

    private JFXTextArea textArea;

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
            addListenerToAnnouncementListView();
            setHandleCellClicked();
            fillCell(item);
            setButtonHandler();
        }
    }

    private void addListenerToAnnouncementListView() {
        getListView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Announcement>() {
            @Override
            public void changed(ObservableValue<? extends Announcement> observableValue, Announcement announcement, Announcement t1) {
                submitButton.setVisible(false);
                hbox.getChildren().remove(textArea);

                if (!hbox.getChildren().contains(messageLabel)) {
                    hbox.getChildren().add(0, messageLabel);
                }
            }
        });
    }

    private void setHandleCellClicked() {
        setOnMouseClicked(event -> {
            String msg = messageLabel.getText();
            hbox.getChildren().remove(messageLabel);
            if (!hbox.getChildren().contains(textArea)) {
                textArea = new JFXTextArea(msg);
                hbox.getChildren().add(0, textArea);
            }
            submitButton.setVisible(true);
        });
    }

    private void fillCell(Announcement item) {
        messageLabel.setText(item.getMessage());
        messageLabel.setMaxWidth(400);
        messageLabel.setWrapText(true);
        setText(null);
        setGraphic(hbox);
    }

    private void setButtonHandler() {
        submitButton.setOnMouseClicked(event -> {
            String msg = textArea.getText();
            getItem().setMessage(msg);
            messageLabel.setText(msg);
        });
    }
}
