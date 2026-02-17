// ----- Here's a class
class Student(val name: String) {           // immutable variable (val)
    val grades = mutableListOf<Int>()       // mutable variable (var-like behavior)
    val courses = mutableListOf<String>()

    fun addGrade(course: String, grade: Int) {
        courses.add(course)
        grades.add(grade)
    }
}

// ----- oh look a function
fun calculateAverage(grades: List<Int>): Double {
    if (grades.isEmpty()) return 0.0
    var sum = 0                             // mutable variable
    for (g in grades) {
        sum += g
    }
    return sum.toDouble() / grades.size
}

fun getLetterGrade(avg: Double): String {
    // Conditionals, map letter grades to their numbers
    return if (avg >= 90) "A"
    else if (avg >= 80) "B"
    else if (avg >= 70) "C"
    else if (avg >= 60) "D"
    else "F"
}

fun printReport(students: List<Student>) {
    println("\n----- Grade Report -----")
    for (student in students) {             // loop
        val avg = calculateAverage(student.grades)
        val letter = getLetterGrade(avg)

        println("${student.name}: Average = %.2f".format(avg) + " ($letter)")
        var i = 0
        for (grade in student.grades) {

            println("   Course: ${student.courses[i]} - Grade: $grade (${getLetterGrade(grade.toDouble())})")
            i++
        }
        println()
    }
}

fun addStudent(students: MutableList<Student>) {
    print("Enter student name: ")
    val name = readln()                     // immutable variables
    val student = Student(name)

    while (true) {                          // loop to enter all the classes and grades
        print("Enter course name (or -1 to stop): ")
        val course = readln()
        if (course == "-1") break

        print("Enter grade (or -1 to stop): ")
        val grade = readln().toInt()
        if (grade == -1) break              // conditional to exit loop

        if (grade in 0..100) {              // conditional validation
            student.addGrade(course,grade)
        } else {
            println("Invalid grade, only input 0 to 100")
        }
    }

    students.add(student)
}

// ----- Main Program -----
fun main() {
    val students = mutableListOf<Student>() // mutable variable

    while (true) {                          // loop (menu system)
        println("\n1. Add Student")
        println("2. Show Report")
        println("3. Exit")
        print("Choose an option: ")

        when (readln().toInt()) {           // more conditionals yay
            1 -> addStudent(students)
            2 -> printReport(students)
            3 -> {
                println("Goodbye.")
                break
            }
            else -> println("Invalid choice.")
        }
    }
}
