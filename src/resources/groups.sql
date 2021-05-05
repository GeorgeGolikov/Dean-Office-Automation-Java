CREATE OR REPLACE PROCEDURE get_groups (groups_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open groups_cursor for
        SELECT name FROM GROUPS;
END;

CREATE OR REPLACE PROCEDURE add_groups (group_name VARCHAR2)
    IS
BEGIN
    INSERT INTO GROUPS
        (name)
    VALUES
        (group_name);
END;

CREATE OR REPLACE PROCEDURE del_groups (group_name VARCHAR2)
    IS
        groupID NUMBER;
BEGIN
    SELECT ID INTO groupID FROM GROUPS WHERE name = group_name;

    commit;
    DELETE FROM MARKS WHERE STUDENT_ID IN (SELECT ID FROM PEOPLE WHERE GROUP_ID = groupID);
    DELETE FROM PEOPLE WHERE GROUP_ID = groupID;
    DELETE FROM GROUPS WHERE name = group_name;
    commit;
END;

CREATE OR REPLACE PROCEDURE upd_groups (old_group_name VARCHAR2, new_group_name VARCHAR2)
    IS
BEGIN
    UPDATE GROUPS SET name = new_group_name WHERE name = old_group_name;
    commit;
END;