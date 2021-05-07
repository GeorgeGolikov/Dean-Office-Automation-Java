package sample.entity;

public final class Mark {
    private Integer id;
    private String subjectName;
    private String teacherName;
    private String teacherLastName;
    private Integer value;

    private Integer studentId;
    private Integer teacherId;

    public Mark() {

    }

    public Mark(Integer value) {
        this.value = value;
    }

    public Mark(Integer id, String subjectName, String teacherName, String teacherLastName, Integer value) {
        this.id = id;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.teacherLastName = teacherLastName;
        this.value = value;
    }

    public Mark(Integer studentId, String subjectName, Integer teacherId, Integer value) {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.teacherId = teacherId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
