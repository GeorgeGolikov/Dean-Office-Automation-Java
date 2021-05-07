package sample.service.impl;

import javafx.collections.ObservableList;
import sample.database.dao.DAO;
import sample.database.dao.TeacherDao;
import sample.entity.Teacher;
import sample.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private DAO dao;

    public TeacherServiceImpl(TeacherDao dao) {
        this.dao = dao;
    }

    @Override
    public ObservableList<Teacher> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(Teacher teacher) {
        dao.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        dao.delete(teacher);
    }

    @Override
    public void update(Teacher _old, Teacher _new) {
        dao.update(_old, _new);
    }

    @Override
    public List<Integer> countDependencies(Teacher teacher) {
        return dao.countDependencies(teacher);
    }
}
