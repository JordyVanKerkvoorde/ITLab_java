package ITLab.controller;

import domain.Session;

public interface Callback {

    void loadCalendar();

    void unloadCalendar();

    void loadSession(Session session);

    void loadCalendarFX();
}