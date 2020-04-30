package ITLab.components;

import domain.model.session.Session;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Create an anchor pane that can store additional data.
 */
public class VBoxNode extends VBox {

    // Date associated with this pane
    private LocalDate date;

    private List<Session> sessions;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public VBoxNode(Node... children) {
        super(children);
        sessions = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}