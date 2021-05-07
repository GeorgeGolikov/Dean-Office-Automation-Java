package sample.database.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DBConnection;
import sample.entity.Group;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class GroupsDao extends DAO<Group> {
    public GroupsDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    @Override
    public ObservableList<Group> findAll() {
        try (CallableStatement cstmt = conn.prepareCall("{call get_groups(?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(1, ResultSet.class)) {
                ObservableList<Group> res = FXCollections.observableArrayList();
                while(rs.next()) {
                    res.add(new Group(rs.getString(1)));
                }
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Group group) {
        try (CallableStatement cstmt = conn.prepareCall("{call add_groups(?)}")) {
            cstmt.setString(1, group.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Group group) {
        try (CallableStatement cstmt = conn.prepareCall("{call del_groups(?)}")) {
            cstmt.setString(1, group.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Group _old, Group _new) {
        try (CallableStatement cstmt = conn.prepareCall("{call upd_groups(?,?)}")) {
            cstmt.setString(1, _old.getName());
            cstmt.setString(2, _new.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Integer> countDependencies(Group group) {
        try (CallableStatement cstmt = conn.prepareCall("{call count_group_dependencies(?,?)}")) {
            cstmt.setString(1, group.getName());
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(2, ResultSet.class)) {
                List<Integer> res = new ArrayList<>();
                if (rs.next()) {
                    res.add(rs.getInt(1));
                    res.add(rs.getInt(2));
                } else
                    res = null;
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
