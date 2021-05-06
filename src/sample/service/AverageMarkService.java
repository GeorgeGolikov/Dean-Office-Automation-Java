package sample.service;

public interface AverageMarkService {
    String calcPerfStud(String start, String end, Integer id);
    String calcPerfTeach(String start, String end, Integer id);
    String calcPerfGroup(String start, String end, String name);
    String calcPerfSubj(String start, String end, String name);
}
