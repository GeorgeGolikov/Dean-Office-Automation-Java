package sample.service.impl;

import javafx.collections.ObservableList;
import sample.database.dao.MarkDao;
import sample.entity.Mark;
import sample.entity.Student;
import sample.service.MarkService;

public class MarkServiceImpl implements MarkService {
    private MarkDao dao;

    public MarkServiceImpl(MarkDao dao) {
        this.dao = dao;
    }

    @Override
    public ObservableList<Mark> findAllByStudent(Student student) {
        return dao.findAllByStudent(student);
    }

    @Override
    public void add(Mark mark) {
        dao.save(mark);
    }

    @Override
    public void delete(Mark mark) {
        dao.delete(mark);
    }

    @Override
    public void update(Mark _old, Mark _new) {
        dao.update(_old, _new);
    }
}
