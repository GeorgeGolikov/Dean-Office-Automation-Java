CREATE OR REPLACE PROCEDURE get_marks (student_idd NUMBER, marks_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open marks_cursor for
        SELECT M.ID, S.NAME, P2.FIRST_NAME, P2.LAST_NAME, VALUE FROM MARKS M
        JOIN PEOPLE P on M.STUDENT_ID = P.ID
        JOIN PEOPLE P2 on P2.ID = M.TEACHER_ID
        JOIN SUBJECTS S on M.SUBJECT_ID = S.ID
        WHERE P.ID = student_idd;
END;

CREATE OR REPLACE PROCEDURE add_marks (student_idd NUMBER, subject_name VARCHAR2,
                                    teacher_idd NUMBER, val NUMBER)
    IS
BEGIN
    INSERT INTO MARKS
        (STUDENT_ID, SUBJECT_ID, TEACHER_ID, VALUE)
    VALUES
        (student_idd, (SELECT ID FROM SUBJECTS WHERE name = subject_name), teacher_idd, val);
END;

CREATE OR REPLACE PROCEDURE del_marks (mark_id NUMBER)
    IS
BEGIN
    DELETE FROM MARKS WHERE ID = mark_id;
END;

CREATE OR REPLACE PROCEDURE upd_marks (mark_id NUMBER, val NUMBER)
    IS
BEGIN
    UPDATE MARKS SET
                     VALUE = val
    WHERE ID = mark_id;
    commit;
END;

-- unused trigger 1
CREATE OR REPLACE TRIGGER check_mark_value
    BEFORE INSERT OR UPDATE ON marks
    FOR EACH ROW
BEGIN
    IF (:NEW.value < 2 OR :NEW.value > 5)
    THEN
        RAISE_APPLICATION_ERROR(-20202, 'Value is not in [2..5]');
    END IF;
END;

-- unused trigger 2