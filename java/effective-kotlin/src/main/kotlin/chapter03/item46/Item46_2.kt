package chapter03.item46

inline fun requestNewToken(
    hasToken: Boolean,
    crossinline onRefresh: () -> Unit,
    noinline onGenerate: () -> Unit
) {
    if (hasToken) {
        httpCall("get-token", onGenerate)
    } else {
        httpCall("refresh-token") {
            onRefresh()
            onGenerate()
        }
    }
}

fun httpCall(url: String, callback: () -> Unit) {}
