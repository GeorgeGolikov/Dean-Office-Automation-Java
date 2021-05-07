package sample.service.impl;

import javafx.collections.ObservableList;
import sample.database.dao.DAO;
import sample.database.dao.SubjectDao;
import sample.entity.Subject;
import sample.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private DAO dao;

    public SubjectServiceImpl(SubjectDao dao) {
        this.dao = dao;
    }

    @Override
    public ObservableList<Subject> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(Subject subject) {
        dao.save(subject);
    }

    @Override
    public void delete(Subject subject) {
        dao.delete(subject);
    }

    @Override
    public void update(Subject _old, Subject _new) {
        dao.update(_old, _new);
    }

    @Override
    public List<Integer> countDependencies(Subject subject) {
        return dao.countDependencies(subject);
    }
}
