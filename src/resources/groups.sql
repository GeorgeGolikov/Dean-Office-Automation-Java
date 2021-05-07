CREATE OR REPLACE PROCEDURE get_groups (groups_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open groups_cursor for
        SELECT name FROM GROUPS;
END;

CREATE OR REPLACE PROCEDURE add_groups (group_name VARCHAR2)
    IS
        groupIdCount NUMBER;
BEGIN
    COMMIT;
    SELECT COUNT(ID) INTO groupIdCount FROM GROUPS WHERE name = group_name;

    IF (groupIdCount = 0)
    THEN
        INSERT INTO GROUPS
            (name)
        VALUES
            (group_name);
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
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

CREATE OR REPLACE PROCEDURE count_group_dependencies (group_name VARCHAR2, count_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open count_cursor for
        SELECT COUNT(P.ID), COUNT(M.ID) FROM GROUPS
        JOIN PEOPLE P on GROUPS.ID = P.GROUP_ID
        JOIN MARKS M on P.ID = M.STUDENT_ID
        WHERE GROUPS.NAME = group_name;
END;

CREATE OR REPLACE PROCEDURE upd_groups (old_group_name VARCHAR2, new_group_name VARCHAR2)
    IS
BEGIN
    UPDATE GROUPS SET name = new_group_name WHERE name = old_group_name;
    commit;
END;