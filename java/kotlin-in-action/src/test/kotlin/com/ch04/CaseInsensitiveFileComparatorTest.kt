package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.io.File

internal class CaseInsensitiveFileComparatorTest : DescribeSpec({

    describe("compare") {
        context("파일 경로를 기준으로 오름차순 정렬을 합니다.") {
            val files = listOf(file2, file1, file1)

            files.sortedWith(CaseInsensitiveFileComparator)
                .shouldContainExactly(listOf(file1, file1, file2))
        }
    }
}) {
    companion object {
        val file1 = File("/Users/developer/Desktop/study/java/kotlin-in-action-1")
        val file2 = File("/Users/developer/Desktop/study/java/kotlin-in-action-2")
    }
}
