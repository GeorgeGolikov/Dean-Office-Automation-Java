package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import sample.database.dao.GroupsDao;
import sample.entity.Group;
import sample.service.GroupService;
import sample.service.impl.GroupServiceImpl;

import java.sql.SQLException;

public class Controller {
    private static GroupService groupService;

    static {
        try {
            groupService = new GroupServiceImpl(new GroupsDao());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        tableGroupsColumn.setCellFactory(
                TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tableGroupsColumn.setOnEditCommit(e -> {
            String newName = e.getNewValue();
            editGroup(e.getTableView().getItems().get(e.getTablePosition().getRow()), newName);
        });
    }

    /* отображение таблицы групп */
    private ObservableList<Group> groups = FXCollections.observableArrayList();

    @FXML
    private Tab tabGroups;

    @FXML
    private TableView<Group> tableGroups;

    @FXML
    private TableColumn<Group, String> tableGroupsColumn;

    @FXML
    private TextField addGroupTf;

    @FXML
    private Button addGroupBut;

    @FXML
    private Button deleteGroupBut;

    // в момент нажатия на tabGroups
    public void loadGroups() {
        groups = groupService.findAll();
        tableGroupsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableGroups.setItems(groups);
    }

    public void addGroup() {
        String groupName = addGroupTf.getText();
        if ("".equals(groupName)) return;

        groupService.add(new Group(groupName));
        loadGroups();
    }

    public void delGroup() {
        Group group = tableGroups.getSelectionModel().getSelectedItem();
        if (group != null) {
            groupService.delete(group);
        }
        loadGroups();
    }

    public void editGroup(Group group, String newGroupName) {
        if (group == null || "".equals(newGroupName)) return;

        groupService.update(group, new Group(newGroupName));
        group.setName(newGroupName);
    }
}
