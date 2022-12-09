package com.spring.sre.chapter02.web

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.time.Duration
import java.util.*

@RestController
class FileDownloadApi {
    private val log = LogManager.getLogger(FileDownloadApi::class)

    @Value("\${appDownloadFilePath}")
    private lateinit var appDownloadFilePath: String

    @GetMapping("/download/{size}", "/download/{size}/{delaySecond}")
    fun downloadFile(
        @PathVariable size: Int,
        @PathVariable(required = false) delaySecond: Long?
    ): ResponseEntity<Resource> {
        if (delaySecond != null && delaySecond > 0) {
            log.info("> Dump file delay download: {}s", delaySecond)
            Thread.sleep(Duration.ofSeconds(delaySecond).toMillis())
        }

        val dumpFile = createDumpFile(appDownloadFilePath)
        val fileMB = (1024 * size) * 1024
       
        log.info("> Dump file path: {}, size: {}mb", dumpFile.canonicalPath, size)
        writeFileByDumpContent(dumpFile, fileMB)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, attachmentContentDisposition(dumpFile).toString())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(UrlResource(dumpFile.toURI()))
    }

    @DeleteMapping("/download/delete/files")
    fun deleteFiles(): ResponseEntity<String> {
        File(appDownloadFilePath).listFiles()?.forEach { it.deleteOnExit() }
        return ResponseEntity.ok("delete")
    }

    private fun attachmentContentDisposition(file: File): ContentDisposition =
        attachmentContentDisposition(file.name)

    private fun attachmentContentDisposition(fileName: String): ContentDisposition {
        return ContentDisposition.attachment()
            .filename(fileName)
            .build()
    }

    private fun writeFileByDumpContent(file: File, byteSize: Int) {
        file.bufferedWriter().use { bw ->
            val sequence = generateSequence(0) { (it + 1) % 26 }.take(byteSize)
            val content = sequence.joinToString(separator = "") { ('a' + it).toString() }
            bw.write(content)
        }
    }

    private fun createDumpFile(path: String): File {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val fileName = "$uuid.txt"
        return File("$path/$fileName").apply { this.parentFile.mkdirs() }
    }
}
