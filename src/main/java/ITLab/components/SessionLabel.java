package ITLab.components;

import ITLab.controller.Callback;
import domain.Session;
import javafx.scene.control.Label;


public class SessionLabel extends Label {


    private Session session;



    public SessionLabel(Session session){
        super(session.getTitle());
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
