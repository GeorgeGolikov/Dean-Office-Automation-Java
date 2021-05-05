CREATE OR REPLACE PROCEDURE get_subjects (subjects_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open subjects_cursor for
        SELECT name FROM SUBJECTS;
END;

CREATE OR REPLACE PROCEDURE add_subjects (subject_name VARCHAR2)
    IS
BEGIN
    INSERT INTO SUBJECTS
        (name)
    VALUES
        (subject_name);
END;

CREATE OR REPLACE PROCEDURE del_subjects (subject_name VARCHAR2)
    IS
BEGIN
    commit;
    DELETE FROM MARKS WHERE SUBJECT_ID IN (SELECT ID FROM SUBJECTS WHERE NAME = subject_name);
    DELETE FROM SUBJECTS WHERE name = subject_name;
    commit;
END;

CREATE OR REPLACE PROCEDURE upd_subjects (old_subject_name VARCHAR2, new_subject_name VARCHAR2)
    IS
BEGIN
    UPDATE SUBJECTS SET name = new_subject_name WHERE name = old_subject_name;
    commit;
END;