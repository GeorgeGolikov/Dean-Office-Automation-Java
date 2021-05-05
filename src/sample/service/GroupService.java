package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Group;

public interface GroupService {
    ObservableList<Group> findAll();
    void add(Group group);
    void delete(Group group);
    void update(Group _old, Group _new);
}
