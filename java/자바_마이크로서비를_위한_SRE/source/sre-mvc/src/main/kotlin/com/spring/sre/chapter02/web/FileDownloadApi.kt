package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.config.Log4j2Support
import com.spring.sre.chapter02.util.toResponseEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.util.unit.DataSize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom

@RestController
class FileDownloadApi(
    private val dumpFileService: DumpFileService,
) {
    @Value("\${appDownloadFilePath}")
    private lateinit var appDownloadFilePath: String

    @GetMapping("/download/{size}", "/download/{size}/{delaySecond}")
    fun downloadFile(
        @PathVariable size: Long,
        @PathVariable(required = false) delaySecond: Long?
    ): ResponseEntity<Resource> {
        delaySecond?.let {
            log.info("덤프 파일 생성 대기 {}초 ...", delaySecond)
            Thread.sleep(Duration.ofSeconds(delaySecond).toMillis())
        }
        return dumpFileService
            .crateFileAndWriteDumpContent(appDownloadFilePath, DataSize.ofMegabytes(size))
            .toResponseEntity()
    }

    @GetMapping("/download/random")
    fun downloadRandomFile(): ResponseEntity<Resource> {
        val size = ThreadLocalRandom.current().nextLong(0, 30) + 1
        return dumpFileService
            .crateFileAndWriteDumpContent(appDownloadFilePath, DataSize.ofMegabytes(size))
            .toResponseEntity()
    }

    @DeleteMapping("/download/delete/files")
    fun deleteFiles(): ResponseEntity<DeleteFilesDto> =
        ResponseEntity.ok(dumpFileService.deleteDumpFiles(appDownloadFilePath))

    companion object : Log4j2Support
}
