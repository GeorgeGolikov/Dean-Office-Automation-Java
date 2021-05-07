package sample.service.impl;

import javafx.collections.ObservableList;
import sample.database.dao.DAO;
import sample.database.dao.StudentDao;
import sample.entity.Student;
import sample.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private DAO dao;

    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public ObservableList<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(Student student) {
        dao.save(student);
    }

    @Override
    public void delete(Student student) {
        dao.delete(student);
    }

    @Override
    public void update(Student _old, Student _new) {
        dao.update(_old, _new);
    }

    @Override
    public List<Integer> countDependencies(Student student) {
        return dao.countDependencies(student);
    }
}
