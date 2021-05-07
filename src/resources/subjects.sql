CREATE OR REPLACE PROCEDURE get_subjects (subjects_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open subjects_cursor for
        SELECT name FROM SUBJECTS;
END;

CREATE OR REPLACE PROCEDURE add_subjects (subject_name VARCHAR2)
    IS
        subjectIdCount NUMBER;
BEGIN
    COMMIT;
    SELECT COUNT(ID) INTO subjectIdCount FROM SUBJECTS WHERE name = subject_name;

    IF (subjectIdCount = 0)
    THEN
        INSERT INTO SUBJECTS
            (name)
        VALUES
            (subject_name);
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
END;

CREATE OR REPLACE PROCEDURE del_subjects (subject_name VARCHAR2)
    IS
BEGIN
    commit;
    DELETE FROM MARKS WHERE SUBJECT_ID IN (SELECT ID FROM SUBJECTS WHERE NAME = subject_name);
    DELETE FROM SUBJECTS WHERE name = subject_name;
    commit;
END;

CREATE OR REPLACE PROCEDURE count_subj_dependencies (subject_name VARCHAR2, count_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open count_cursor for
        SELECT COUNT(M.ID) FROM SUBJECTS
        JOIN MARKS M on SUBJECTS.ID = M.SUBJECT_ID
        WHERE SUBJECTS.NAME = subject_name;
END;

CREATE OR REPLACE PROCEDURE upd_subjects (old_subject_name VARCHAR2, new_subject_name VARCHAR2)
    IS
BEGIN
    UPDATE SUBJECTS SET name = new_subject_name WHERE name = old_subject_name;
    commit;
END;