package chapter02.item24

sealed class Response<out R, out E>
class Failure<out E>(val error: E) : Response<Nothing, E>()
class Success<out R>(val value: R) : Response<R, Nothing>()
