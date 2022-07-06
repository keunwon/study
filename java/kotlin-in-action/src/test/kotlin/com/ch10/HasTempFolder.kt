package com.ch10

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class HasTempFolder {
    @get:Rule
    val folder = TemporaryFolder()

    @Test
    fun testUsingTempFolder() {
        val createdFile = folder.newFile("myfile.txt")
        val createdFolder = folder.newFolder("subfolder")
    }
}
