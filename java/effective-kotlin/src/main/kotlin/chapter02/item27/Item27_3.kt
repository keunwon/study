package chapter02.item27

import chapter02.item27.MessageLength.LONG
import chapter02.item27.MessageLength.SHORT

interface MessageDisplay2 {
    fun show(message: String, duration: MessageLength = LONG)
}

class ToastDisplay(val context: Context) : MessageDisplay2 {

    override fun show(message: String, duration: MessageLength) {
        val toastDuration = when (duration) {
            SHORT -> 10
            LONG -> 100
        }
    }
}

