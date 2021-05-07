CREATE OR REPLACE PROCEDURE get_teachers (teachers_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open teachers_cursor for
        SELECT PEOPLE.ID, FIRST_NAME, LAST_NAME, FATHER_NAME FROM PEOPLE
        WHERE TYPE = 'T';
END;

CREATE OR REPLACE PROCEDURE add_teachers (first_namee VARCHAR2, last_namee VARCHAR2,
                                          father_namee VARCHAR2)
    IS
BEGIN
    INSERT INTO PEOPLE
        (FIRST_NAME, LAST_NAME, FATHER_NAME, TYPE)
    VALUES
        (first_namee, last_namee, father_namee, 'T');
END;

CREATE OR REPLACE PROCEDURE del_teachers (teacherID NUMBER)
    IS
BEGIN
    commit;
    DELETE FROM MARKS WHERE TEACHER_ID = teacherID;
    DELETE FROM PEOPLE WHERE ID = teacherID;
    commit;
END;

CREATE OR REPLACE PROCEDURE count_teach_dependencies (teacherID NUMBER, count_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open count_cursor for
        SELECT COUNT(M.ID) FROM PEOPLE
        JOIN MARKS M on PEOPLE.ID = M.TEACHER_ID
        WHERE PEOPLE.ID = teacherID;
END;

CREATE OR REPLACE PROCEDURE upd_teachers (teacher_id NUMBER, first_namee VARCHAR2,
                                        last_namee VARCHAR2, father_namee VARCHAR2)
    IS
BEGIN
    UPDATE PEOPLE SET
                      FIRST_NAME = first_namee,
                      LAST_NAME = last_namee,
                      FATHER_NAME = father_namee
    WHERE ID = teacher_id;
    commit;
END;