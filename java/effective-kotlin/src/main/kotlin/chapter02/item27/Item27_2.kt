package chapter02.item27

import chapter02.item27.MessageLength.LONG
import chapter02.item27.MessageLength.SHORT

class Context

class MessageDisplay(val context: Context) {

    fun show(message: String, duration: MessageLength = LONG) {
        val toastDuration = when (duration) {
            SHORT -> 10
            LONG -> 100
        }
    }
}

enum class MessageLength { SHORT, LONG }

fun main() {
    val messageDisplay = MessageDisplay(Context())
    messageDisplay.show(message = "message", duration = LONG)
}
