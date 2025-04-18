package algorithm.programmers

class Lesson42579 {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.zip(plays.toList())
            .mapIndexed { index, (genre, play) -> Music(index, genre, play) }
            .groupBy { it.genre }.entries
            .sortedByDescending { (_, list) -> list.sumOf { it.play } }
            .flatMap { (_, list) -> list.sortedWith(compareBy({ -it.play }, { it.index })).take(2).map { it.index } }
            .toIntArray()
    }

    private data class Music(val index: Int, val genre: String, val play: Int)
}
