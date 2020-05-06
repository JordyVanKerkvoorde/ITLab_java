package ITLab.controller;

import domain.model.session.Session;

public interface Callback {

    void loadCalendar();

    void loadAnnouncements();

    void loadStatistics();

    void loadUsers();
}