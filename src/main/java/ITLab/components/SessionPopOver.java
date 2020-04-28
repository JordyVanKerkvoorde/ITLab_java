package ITLab.components;

import com.jfoenix.controls.JFXDatePicker;
import domain.model.session.Session;
import domain.model.session.SessionEntry;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.PopOver;

public class SessionPopOver {

    private TextField title;
    private TextField description;
    private JFXDatePicker start;
    private JFXDatePicker end;



    public SessionPopOver(SessionEntry<Session> sessionEntry){
        title.setText(sessionEntry.getTitle());
        description.setText(sessionEntry.getDescription());
    }


}
