package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.config.Log4j2Support
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.unit.DataSize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.time.Duration
import java.util.*

@RestController
class FileDownloadApi {
    @Value("\${appDownloadFilePath}")
    private lateinit var appDownloadFilePath: String

    @GetMapping("/download/{size}", "/download/{size}/{delaySecond}")
    fun downloadFile(
        @PathVariable size: Int,
        @PathVariable(required = false) delaySecond: Long?
    ): ResponseEntity<Resource> {
        delaySecond?.let {
            log.info("덤프 파일 생성 대기 중 {}초 ...", delaySecond)
            Thread.sleep(Duration.ofSeconds(delaySecond).toMillis())
        }

        val dumpFile = createDumpFile(appDownloadFilePath).apply {
            log.info("> 덤프 파일 생성 경로: {}, 용량: {}MB", this.path, DataSize.ofMegabytes(this.length()))
            writeFileByDumpContent(this, DataSize.ofMegabytes(size.toLong()))
        }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, attachmentContentDisposition(dumpFile).toString())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(UrlResource(dumpFile.toURI()))
    }

    @DeleteMapping("/download/delete/files")
    fun deleteFiles(): ResponseEntity<DeleteFilesDto> {
        val fileInfoList = File(appDownloadFilePath).listFiles()?.map { file ->
            val fileInfo = FileInfo(file)
            file.deleteOnExit()
            fileInfo
        } ?: emptyList()
        val fileDeleteFilesDto = fileInfoList
            .run { DeleteFilesDto(appDownloadFilePath, this) }
            .also { log.info("> 삭제 대상 파일 경로: {}, 파일 개수: {}", it.rootPath, it.size) }
        return ResponseEntity.ok(fileDeleteFilesDto)
    }

    private fun attachmentContentDisposition(file: File): ContentDisposition =
        attachmentContentDisposition(file.name)

    private fun attachmentContentDisposition(fileName: String): ContentDisposition {
        return ContentDisposition.attachment()
            .filename(fileName)
            .build()
    }

    private fun writeFileByDumpContent(file: File, size: DataSize) {
        if (Int.MAX_VALUE < size.toBytes()) {
            throw IllegalArgumentException("너무 큰 파일을 생성하려고 합니다. ${size.toMegabytes()}MB")
        }
        file.bufferedWriter().use { bw ->
            val sequence = generateSequence(0) { (it + 1) % 26 }.take(size.toBytes().toInt())
            val content = sequence.joinToString(separator = "") { ('a' + it).toString() }
            bw.write(content)
        }
    }

    private fun createDumpFile(dirPath: String): File {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val path = "${dirPath}/${uuid}.txt"
        return File(path).apply { this.parentFile.mkdirs() }
    }

    companion object : Log4j2Support
}

data class DeleteFilesDto(
    val rootPath: String,
    val size: Int,
    val files: List<FileInfo>,
) {
    constructor(rootPath: String, files: List<FileInfo>) : this(rootPath, files.size, files)
}

data class FileInfo(
    private val path: String,
    private val name: String,
    private val size: Long,
) {
    constructor(file: File) : this(file.path, file.name, file.length())
}
