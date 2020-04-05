package ITLab.controller;

import com.jfoenix.controls.JFXListView;
import domain.MockData;
import domain.Session;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionController implements Initializable {
    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private JFXListView<User> ingeschrevenList;

    private Session session;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (User user : MockData.mockUsers) {
            ingeschrevenList.getItems().add(user);
        }
        descriptionTextArea.setWrapText(true);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
        updateSession();
    }
    public void updateSession(){
        titleTextField.setText(session.getTitle());
        descriptionTextArea.setText(session.getDescription());
    }
}
