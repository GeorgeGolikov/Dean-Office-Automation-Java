-- средний балл по всем предметам в интервале времени от startYear до endYear(включительно)
-- конкретного студента
CREATE OR REPLACE PROCEDURE calcPerfStud(startYear IN VARCHAR2, endYear IN VARCHAR2,
                                        studentID IN NUMBER, avg_mark_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open avg_mark_cursor for
        SELECT AVG(marks.value)
        FROM
            marks
                JOIN people ON marks.student_id = people.id
                JOIN groups ON people.group_id = groups.id
        WHERE SUBSTR(groups.name, 8) >= startYear AND SUBSTR(groups.name, 8) <= endYear
        AND MARKS.STUDENT_ID = studentID;
END calcPerfStud;

-- средний балл по всем предметам в интервале времени от startYear до endYear(включительно)
-- конкретной группы
CREATE OR REPLACE PROCEDURE calcPerfGroup(startYear IN VARCHAR2, endYear IN VARCHAR2,
                                         group_name IN VARCHAR2, avg_mark_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open avg_mark_cursor for
        SELECT AVG(marks.value)
        FROM
            marks
                JOIN people ON marks.student_id = people.id
                JOIN groups ON people.group_id = groups.id
        WHERE SUBSTR(groups.name, 8) >= startYear AND SUBSTR(groups.name, 8) <= endYear
        AND GROUPS.NAME = group_name;
END calcPerfGroup;

-- средний балл в интервале времени от startYear до endYear(включительно)
-- конкретного предмета
CREATE OR REPLACE PROCEDURE calcPerfSubj(startYear IN VARCHAR2, endYear IN VARCHAR2,
                                        subj_name IN VARCHAR2, avg_mark_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open avg_mark_cursor for
        SELECT AVG(marks.value)
        FROM
            marks
                JOIN people ON marks.student_id = people.id
                JOIN groups ON people.group_id = groups.id
                JOIN SUBJECTS S on MARKS.SUBJECT_ID = S.ID
        WHERE SUBSTR(groups.name, 8) >= startYear AND SUBSTR(groups.name, 8) <= endYear
        AND S.NAME = subj_name;
END calcPerfSubj;

-- средний балл по всем предметам в интервале времени от startYear до endYear(включительно)
-- конкретного преподавателя
CREATE OR REPLACE PROCEDURE calcPerfTeacher(startYear IN VARCHAR2, endYear IN VARCHAR2,
                                           teacherID IN NUMBER, avg_mark_cursor OUT SYS_REFCURSOR)
    IS
BEGIN
    open avg_mark_cursor for
        SELECT AVG(marks.value)
        FROM
            marks
                JOIN people ON marks.student_id = people.id
                JOIN groups ON people.group_id = groups.id
        WHERE SUBSTR(groups.name, 8) >= startYear AND SUBSTR(groups.name, 8) <= endYear
        AND MARKS.TEACHER_ID = teacherID;
END calcPerfTeacher;