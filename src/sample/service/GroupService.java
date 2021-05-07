package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Group;

import java.util.List;

public interface GroupService {
    ObservableList<Group> findAll();
    void add(Group group);
    void delete(Group group);
    void update(Group _old, Group _new);

    List<Integer> countDependencies(Group group);
}
