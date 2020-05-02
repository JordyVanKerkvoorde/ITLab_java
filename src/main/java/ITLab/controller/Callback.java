package ITLab.controller;

import domain.model.session.Session;

public interface Callback {

    void loadCalendar();

    void loadSession(Session session);

    void loadEntry();

    void loadAnnouncements();

    void loadStatistics();

    void loadUsers();
}