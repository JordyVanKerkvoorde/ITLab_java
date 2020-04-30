package domain.model.session;

import com.calendarfx.model.Entry;
import domain.model.user.User;
import javafx.beans.property.StringProperty;

public class SessionEntry<Session> extends Entry<Session> {

    private StringProperty description;
//    Properties van maken?
    private Location location;
    private User responsible;

    public SessionEntry() {
        super();
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Location getSessionLocation() {
        return location;
    }

    public void setSessionLocation(Location location) {
        this.location = location;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

}
