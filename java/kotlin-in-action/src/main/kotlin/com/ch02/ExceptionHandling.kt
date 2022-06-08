package com.ch02

import java.io.BufferedReader
import java.io.StringReader

fun readNumber(reader: BufferedReader): Int? {
    return try {
        val line = reader.readLine()
        line.toInt()
    } catch (e: NumberFormatException) {
        null
    } finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        reader.readLine().toInt()
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}

fun readerNumber3(reader: BufferedReader) {
    val number = try {
        reader.readLine().toInt()
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

fun main() {
    readNumber2(BufferedReader(StringReader("aaa")))
    readerNumber3(BufferedReader(StringReader("aaa")))
}