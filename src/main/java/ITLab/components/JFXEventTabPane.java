package ITLab.components;

import ITLab.controller.PopOverController;
import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXTabPane;
import domain.model.session.Session;
import javafx.beans.property.Property;

public class JFXEventTabPane extends JFXTabPane {
    private Entry<Session> sessionEntry;
    private PopOverController popOverController;

    public PopOverController getPopOverController() {
        return popOverController;
    }

    public void setPopOverController(PopOverController popOverController) {
        this.popOverController = popOverController;
    }

    public Entry<Session> getSessionEntry() {
        return sessionEntry;
    }

    public JFXEventTabPane setSession(Entry<Session> sessionEntry) {
        this.sessionEntry = sessionEntry;
        popOverController.setSessionEntry();
        return this;
    }
}
