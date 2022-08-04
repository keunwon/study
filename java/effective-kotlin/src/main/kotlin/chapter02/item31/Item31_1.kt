package chapter02.item31

import chapter02.item31.MessageLength.LONG
import chapter02.item31.MessageLength.SHORT

class Context
enum class MessageLength { SHORT, LONG }

/**
 * 이 프로젝트에서 짧은 메시지를 사욯자에게
 * 출력할 때 사용하는 기본적인 방식입니다.
 *
 * @param message 사용자에게 보여 줄 메시지
 * @param duration 메시지의 길이가
 * 어느 정도 되는지 나타내는 enum 값
 */
fun Context.showMessage(
    message: String,
    duration: MessageLength = LONG
) {
    val toastDuration = when (duration) {
        SHORT -> 10
        LONG -> 100
    }
    println("message: $message, duration: $toastDuration")
}
