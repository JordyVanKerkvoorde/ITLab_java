package ITLab.controller;

import com.jfoenix.controls.*;
import domain.controllers.AnnouncementController;
import domain.model.session.Announcement;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnnouncementsViewController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private JFXTextArea inputArea;
    @FXML
    private JFXButton commitButton;
    @FXML
    private JFXListView<Announcement> announcementsListView;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane anchorPane;

    private ObservableList<Announcement> announcementObservableList;

    private static AnnouncementController announcementController = AnnouncementController.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        announcementObservableList = FXCollections.observableArrayList();
        Thread thread = new Thread(() -> {
            announcementObservableList.addAll(announcementController.getAnnouncements());
            announcementsListView.setItems(announcementObservableList);
            announcementsListView.getItems().sort((o1, o2) -> {
                if (o1.getPostTime().equals(o2.getPostTime())) {
                    return 0;
                }
                if (o1.getPostTime().isBefore(o2.getPostTime())) {
                    return 1;
                } else {
                    return 0;
                }
            });
        });
        thread.start();
        announcementsListView.setCellFactory(announcementListView -> new AnnouncementCell());
        commitButton.setOnAction(event -> createAnnouncement(event));
        setStyle();
    }

    private void setStyle() {
        Font titleFont = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 50);
        anchorPane.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("stylesheet/announcementsview.css")).toExternalForm());
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 12);
        inputArea.setFont(font);
        title.setFont(titleFont);
        announcementsListView.setMaxHeight(Double.MAX_VALUE);
        Bindings.bindBidirectional(anchorPane.prefHeightProperty(), vbox.prefHeightProperty());
        VBox.setVgrow(announcementsListView, Priority.ALWAYS);
        //announcementsListView.prefHeightProperty().bindBidirectional(anchorPane.prefHeightProperty());
    }

    private void createAnnouncement(ActionEvent event) {
        try {
            Announcement announcement = new Announcement(inputArea.getText());
//            ann
            announcementController.addAnnouncement(announcement);
            announcementObservableList.add(0, announcement);
            inputArea.clear();
        } catch (IllegalArgumentException e) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Fout"));
            content.setBody(new Text("Aankondiging mag niet leeg zijn!\nGelieve een aankondiging in te vullen."));
            StackPane stackPane = new StackPane();
            stackPane.autosize();
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.LEFT, true);
            JFXButton button = new JFXButton("OK");
            button.setOnAction(event1 -> dialog.close());
            button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            button.setPrefHeight(32);
            content.setActions(button);
            anchorPane.getChildren().add(stackPane);
            AnchorPane.setTopAnchor(stackPane, (inputArea.getHeight() - content.getPrefHeight()) / 2);
            AnchorPane.setLeftAnchor(stackPane, (inputArea.getWidth() - content.getPrefWidth()) / 2);
            dialog.show();
        }
    }


    /**
     * Announcement is a custom cell for the JFXListView.
     */
    static class AnnouncementCell extends JFXListCell<Announcement> {

        @FXML
        private Label messageLabel;
        @FXML
        private VBox vbox;
        @FXML
        private Label timestampLabel;

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
                setCellDoubleClickedHandler(item);
                fillCell(item);
            }

        }

        /**
         * Sets the action of a double click on a AnnouncementCell
         * Loads an Edit Announcement Popover when double clicked
         * @param announcement : the announcement to be edited
         */
        private void setCellDoubleClickedHandler(Announcement announcement) {
            setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    try {
                        AnnouncementPopoverController controller = new AnnouncementPopoverController(announcement);
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/announcementpopover.fxml"));
                        loader.setController(controller);
                        AnchorPane anchorPane = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Aankondiging aanpassen");
                        stage.setResizable(false);
                        stage.initStyle(StageStyle.UNDECORATED);
                        Scene scene = new Scene(anchorPane);
                        scene.setFill(Color.TRANSPARENT);
                        stage.setScene(scene);
                        stage.showAndWait();
                        updateItem(announcement, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * Fills the AnnouncementCell label based on Announcement in the listView
         * @param item : the Announcement containing the data
         */
        private void fillCell(Announcement item) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - HH:mm", new Locale("nl"));
            timestampLabel.setText(item.getPostTime().format(formatter));
            messageLabel.setText(item.getMessage());
            messageLabel.setWrapText(true);
            messageLabel.prefWidthProperty().bind(getListView().widthProperty());
            messageLabel.setMinHeight(Region.USE_PREF_SIZE);
            this.prefWidthProperty().bind(getListView().widthProperty().multiply(0.95));
            Font textFont = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Roboto-Medium.ttf"), 12);
            messageLabel.setFont(textFont);
            timestampLabel.setFont(textFont);
            messageLabel.setStyle("-fx-font-size: 18");
            setText(null);
            setGraphic(vbox);
        }
    }
}
