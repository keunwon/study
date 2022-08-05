package chapter02.item36

abstract class LoaderWithProgress {

    fun load() {
        // 프로그레스 바를 보여 줌
        innerLoad()
        // 프로그레스 바를 숨김
    }

    abstract fun innerLoad()
}

class ProfileLoader : LoaderWithProgress() {
    override fun innerLoad() {
        // 프로파일을 읽어 들임
    }
}

class ImageLoader : LoaderWithProgress() {
    override fun innerLoad() {
        // 이미지를 읽어 드림
    }
}
