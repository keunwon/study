package chapter02.item36

class Progress {
    fun showProgress() {}
    fun hideProgress() {}
}

class ProfileLoader2 {
    private val progress = Progress()

    fun load() {
        progress.showProgress()
        progress.hideProgress()
    }
}

class ImageLoader2 {
    private val progress = Progress()

    fun load() {
        progress.showProgress()
        progress.hideProgress()
    }
}

abstract class InternetLoader (private val showAlert: Boolean) {

    fun load() {
        innerLoad()
        if (showAlert) {}
    }

    abstract fun innerLoad()
}

class ProfileLoader3 : InternetLoader(showAlert = true) {
    override fun innerLoad() {
        // 프로파일을 읽어 들임
    }
}

class ImageLoader3 : InternetLoader(showAlert =  false) {
    override fun innerLoad() {
        // 이미지를 읽어 들임
    }
}
