package sample.service.impl;

import javafx.collections.ObservableList;
import sample.database.dao.DAO;
import sample.database.dao.GroupsDao;
import sample.entity.Group;
import sample.service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private DAO dao;

    public GroupServiceImpl(GroupsDao dao) {
        this.dao = dao;
    }

    @Override
    public ObservableList<Group> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(Group group) {
        dao.save(group);
    }

    @Override
    public void delete(Group group) {
        dao.delete(group);
    }

    @Override
    public void update(Group _old, Group _new) {
        dao.update(_old, _new);
    }

    @Override
    public List<Integer> countDependencies(Group group) {
        return dao.countDependencies(group);
    }
}
