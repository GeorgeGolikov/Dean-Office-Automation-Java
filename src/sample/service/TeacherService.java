package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Teacher;

public interface TeacherService {
    ObservableList<Teacher> findAll();
    void add(Teacher teacher);
    void delete(Teacher teacher);
    void update(Teacher _old, Teacher _new);
}
