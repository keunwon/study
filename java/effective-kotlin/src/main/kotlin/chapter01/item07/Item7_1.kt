package chapter01.item07

inline fun <reified T> String.readObjectOrNull(): T? {
    return "success" as T
}

inline fun <reified T> String.readObject(): Result<T> {
    if (incorrectSign()) {
        return Failure(JsonParsingException())
    }
    return Success("success" as T)
}

fun incorrectSign() = false

sealed class Result<out T>
class Success<out T>(val result: T): Result<T>()
class Failure(val throwable: Throwable): Result<Nothing>()

class JsonParsingException : Exception()


fun main() {
    val obj = "aaa".readObjectOrNull<String>()
}
