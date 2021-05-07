package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Student;

import java.util.List;

public interface StudentService {
    ObservableList<Student> findAll();
    void add(Student student);
    void delete(Student student);
    void update(Student _old, Student _new);

    List<Integer> countDependencies(Student student);
}
