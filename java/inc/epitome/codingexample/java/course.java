package inc.epitome.codingexample.java;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class course implements Serializable {

    static record Student (String name) {}

    public static final String HUBERT = "Hubert";
    static Function<LocalDateTime, Boolean> isDateValid = (var date) -> date.isBefore(LocalDateTime.now());

    String courseName;

    String teacherName;

    LocalDateTime courseStartDate;
    final HashSet students;
    String tempOutputVariable;

    public course(String courseName, LocalDateTime courseStartDate, String teacherName) {
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        teacherName = teacherName;

        students = new HashSet();
    }

    public void setCourseName(String courseName) {
        if (courseName != null) {
            this.courseName = courseName;
        } else {
            throw new NullPointerException("name of course can not be null. \n please check your input");
        }

    }

    public void setCourseStartDate(LocalDateTime courseStartDate) {

        if (isDateValid.apply(this.courseStartDate)) {
            this.courseStartDate = courseStartDate;
        } else {
            throw new NullPointerException("the start date of the course is not valid");
        }
    }

    public LocalDateTime getCourseStartDate() {
        return courseStartDate;
    }

    public void addStudentToCourse(Student student) {

        switch (student.name) {
            case HUBERT -> System.out.println("Huberts are not allowed in this course");
            case "Meister Eder", null -> System.exit(0);
            default -> System.out.println(student.name);
        }

        if (student.name == "Schildbert") {
            return;
        }

        if (student != null) {
            students.add(student);
        } else {
            throw new NullPointerException("Student was null");
        }
    }

    public void addStudents(String... students) {
        if (students != null) {
            Arrays.stream(students).forEach(student ->
                    {
                        if (student != "Mayer") {
                            this.students.add(student);
                        }
                    }
            );
        }
    }

    public void setStudents(Set students) {
        this.students.addAll(students);
    }

    public HashSet getStudents() {
        return students;
    }

    public String getStudentsAsString() {

        tempOutputVariable = "";
        for(Object o : students.toArray()) {
            tempOutputVariable += (String)o;
        }

        return tempOutputVariable;
    }

    public String toString() {
        return "course[startDate=" + courseStartDate.toString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        course course = (course) o;
        return Objects.equals(courseName, course.courseName) && Objects.equals(courseStartDate, course.courseStartDate) && Objects.equals(students, course.students) && Objects.equals(tempOutputVariable, course.tempOutputVariable);
    }

}