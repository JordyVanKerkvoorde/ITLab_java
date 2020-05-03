package ITLab.components;

import ITLab.controller.PopOverController;
import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXTabPane;
import domain.model.session.Session;
import javafx.beans.property.Property;

public class JFXEventTabPane extends JFXTabPane {
    private Session session;
    private PopOverController popOverController;

    public PopOverController getPopOverController() {
        return popOverController;
    }

    public void setPopOverController(PopOverController popOverController) {
        this.popOverController = popOverController;
    }

    public Session getSession() {
        return session;
    }

    public JFXEventTabPane setSession(Session session) {
        this.session = session;
        popOverController.setSessionEntry();
        return this;
    }
}
