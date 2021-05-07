package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Subject;

import java.util.List;

public interface SubjectService {
    ObservableList<Subject> findAll();
    void add(Subject subject);
    void delete(Subject subject);
    void update(Subject _old, Subject _new);

    List<Integer> countDependencies(Subject subject);
}
