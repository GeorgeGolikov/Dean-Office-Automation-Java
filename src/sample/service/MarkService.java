package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Mark;
import sample.entity.Student;

public interface MarkService {
    ObservableList<Mark> findAllByStudent(Student student);
    void add(Mark mark);
    void delete(Mark mark);
    void update(Mark _old, Mark _new);
}
