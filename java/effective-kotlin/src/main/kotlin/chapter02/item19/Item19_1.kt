package chapter02.item19

class Student {

    fun isPassing(): Boolean =
        calculatePointsFromPassedCourses() > 15

    fun qualifiesForScholarship(): Boolean =
        calculatePointsFromPassedCourses() > 30

    private fun calculatePointsFromPassedCourses(): Int = 0
}
