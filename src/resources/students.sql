CREATE OR REPLACE PROCEDURE get_students (students_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open students_cursor for
        SELECT PEOPLE.ID, FIRST_NAME, LAST_NAME, FATHER_NAME, NAME FROM PEOPLE
        JOIN GROUPS G on PEOPLE.GROUP_ID = G.ID
        WHERE TYPE = 'S';
END;

CREATE OR REPLACE PROCEDURE add_students (first_namee VARCHAR2, last_namee VARCHAR2,
                                        father_namee VARCHAR2, group_name VARCHAR2)
    IS
        groupIdCount NUMBER;
BEGIN
    COMMIT;
    SELECT COUNT(ID) INTO groupIdCount FROM GROUPS WHERE name = group_name;

    IF (groupIdCount != 0)
    THEN
        INSERT INTO PEOPLE
            (FIRST_NAME, LAST_NAME, FATHER_NAME, GROUP_ID, TYPE)
        VALUES
            (first_namee, last_namee, father_namee, (SELECT id FROM GROUPS WHERE name = group_name), 'S');
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
END;

CREATE OR REPLACE PROCEDURE del_students (studentID NUMBER)
    IS
BEGIN
    commit;
    DELETE FROM MARKS WHERE STUDENT_ID = studentID;
    DELETE FROM PEOPLE WHERE ID = studentID;
    commit;
END;

CREATE OR REPLACE PROCEDURE upd_students (student_id NUMBER, first_namee VARCHAR2, last_namee VARCHAR2,
                                          father_namee VARCHAR2, group_name VARCHAR2)
    IS
BEGIN
    UPDATE PEOPLE SET
                      FIRST_NAME = first_namee,
                      LAST_NAME = last_namee,
                      FATHER_NAME = father_namee,
                      GROUP_ID = (SELECT id FROM GROUPS WHERE name = group_name)
    WHERE ID = student_id;
    commit;
END;