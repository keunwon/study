package chapter02.item39

data class Exercise(val first: String, val second: String)

sealed class WorkoutState

class PrepareState(val exercise: Exercise) : WorkoutState()

class ExerciseState(val exercise: Exercise) : WorkoutState()

object DoneState : WorkoutState()

fun List<Exercise>.toStates(): List<WorkoutState> =
    flatMap { listOf(PrepareState(it), ExerciseState(it)) } + DoneState

fun main() {
    val list = listOf(
        Exercise("a", "b"),
        Exercise("aa", "bb"),
        Exercise("aaa", "bbb"),
        Exercise("aaaa", "bbbb"),
    )

    val l = list.toStates()
    println(l)
}
