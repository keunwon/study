package chapter01.item09

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun countCharactersInFile(path: String): Int {
    val reader = BufferedReader(FileReader(path))
    reader.use {
        return reader.lineSequence().sumOf { it.length }
    }
}

fun countCharacterInFile2(path: String): Int {
    BufferedReader(FileReader(path)).use { reader ->
        return reader.lineSequence().sumOf { it.length }
    }
}

fun countCharactersInFile3(path: String): Int {
    File(path).useLines { lines ->
        return lines.sumOf { it.length }
    }
}

fun countCharacterInFile4(path: String): Int =
    File(path).useLines { lines ->
        lines.sumOf { it.length }
    }

fun countCharactersInFile5(path: String): Int {
    FileReader(path).useLines { lines ->
        return lines.sumOf { it.length }
    }
}
