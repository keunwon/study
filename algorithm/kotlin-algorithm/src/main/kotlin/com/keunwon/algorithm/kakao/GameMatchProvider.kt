package com.keunwon.algorithm.kakao

class GameMatchProvider(
    private val gameMatchApi: GameMatchApi,
) {
    fun simulation() {
    }
}

fun main() {
    val url = "https://huqeyhi95c.execute-api.ap-northeast-2.amazonaws.com/prod"
    val authApi = createApi(url, AuthApi::class.java) {
        it.header("Content-Type", "application/json")
        it.header("X-Auth-Token", "532ee78261912ad2ee492f67202df8d6")
    }
    val authKey = authApi.fetchAuthKey(AuthKeyRequest(1)).also { println("authKey: $it") }

    val gameMathApi = createApi(url, GameMatchApi::class.java) {
        it.header("Content-Type", "application/json")
        it.header("Authorization", "b4fbe409-e835-4a97-be28-e02a4cac2f1a")
        it.header("Authorization", authKey.authKey)
    }

    GameMatchProvider(gameMathApi).simulation()
}
