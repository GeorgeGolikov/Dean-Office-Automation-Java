package sample.service.impl;

import sample.database.dao.AverageMarkDao;
import sample.service.AverageMarkService;

public class AverageMarkServiceImpl implements AverageMarkService {
    private AverageMarkDao dao;

    public AverageMarkServiceImpl(AverageMarkDao dao) {
        this.dao = dao;
    }

    @Override
    public String calcPerfStud(String start, String end, Integer id) {
        return dao.calcPerfStud(start, end, id);
    }

    @Override
    public String calcPerfTeach(String start, String end, Integer id) {
        return dao.calcPerfTeacher(start, end, id);
    }

    @Override
    public String calcPerfGroup(String start, String end, String name) {
        return dao.calcPerfGroup(start, end, name);
    }

    @Override
    public String calcPerfSubj(String start, String end, String name) {
        return dao.calcPerfSubj(start, end, name);
    }
}
