package inc.epitome.codingexample.kotlin

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import java.util.function.Function

class course(courseName: String, courseStartDate: LocalDateTime, teacherName: String?) : Serializable {

    companion object {
        const val HUBERT = "Hubert"
        @JvmField
        var isDateValid = Function { date: LocalDateTime -> date.isBefore(LocalDateTime.now()) }
    }

    var courseName: String? = null
        set(courseName) {

                if (courseName != null) {
                    this.courseName = courseName
                } else {
                    throw NullPointerException("name of course can not be null. \n please check your input")
                }

        }

    var teacherName: String? = null

    var courseStartDate: LocalDateTime? = null
        set(date) {
            if (date != null && isDateValid.apply(date)) {
                field = courseStartDate
            } else {
                throw NullPointerException("the start date of the course is not valid")
            }
        }

    val students: HashSet<student>

    var tempOutputVariable: String? = null

    init {
        var teacherName = teacherName
        this.courseName = courseName
        this.courseStartDate = courseStartDate
        teacherName = teacherName
        students = HashSet<student>()
    }

    fun addStudentToCourse(student: student?) {
        when (student?.name) {
            HUBERT -> println("Huberts are not allowed in this course")
            "Meister Eder", null -> System.exit(0)
            else -> println(student?.name)
        }
        if (student?.name === "Schildbert") {
            return
        }
        if (student != null) {
            students.add(student)
        } else {
            throw NullPointerException("Student was null")
        }
    }

    fun addStudents(vararg students: student) {
        if (students != null) {
            Arrays.stream(students).forEach { student: student ->
                if (student !== null) {
                    this.students.add(student)
                }
            }
        }
    }

    fun setStudents(students: Collection<student>) {
        this.students.addAll(students)
    }

    fun returnStudents(): HashSet<*> {
        return students
    }

    val studentsAsString: String
        get() {
            tempOutputVariable = ""
            for (o in students.toTypedArray()) {
                tempOutputVariable += o as String
            }
            return tempOutputVariable!!
        }

    override fun toString(): String {
        return "course[startDate=$courseStartDate]"
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val course = o as course
        return courseName == course.courseName && courseStartDate == course.courseStartDate && students == course.students && tempOutputVariable == course.tempOutputVariable
    }

}