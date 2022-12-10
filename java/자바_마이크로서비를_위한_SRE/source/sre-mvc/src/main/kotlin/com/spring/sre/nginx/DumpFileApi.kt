package com.spring.sre.nginx

import com.spring.sre.nginx.util.toResponseEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.util.unit.DataSize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom

@RestController
class DumpFileApi(
    private val dumpFileService: DumpFileService,
) {
    @Value("\${appDownloadFilePath}")
    private lateinit var appDownloadFilePath: String

    @PostMapping("/dump/create")
    fun creatAndDownloadDumpFile(@ModelAttribute dto: CreateDumpFile): ResponseEntity<Resource> {
        if (dto.hasDelay()) {
            log.info("> 덤프 파일 생성 대기 {}초", dto.delaySecond)
            Thread.sleep(Duration.ofSeconds(dto.delaySecond).toMillis())
        }
        return dumpFileService
            .createFileAndWriteDumpContent(appDownloadFilePath, dto.toDataSize())
            .toResponseEntity()
    }

    @PostMapping("/dump/rand-create")
    fun createAndDownloadRandomDumpFile(): ResponseEntity<Resource> {
        return dumpFileService
            .createFileAndWriteDumpContent(appDownloadFilePath, randDataSize())
            .toResponseEntity()
    }

    @DeleteMapping("/dump/delete")
    fun deleteFiles(): ResponseEntity<DeleteFilesDto> =
        ResponseEntity.ok(dumpFileService.deleteDumpFiles(appDownloadFilePath))

    private fun randDataSize(range: LongRange = 0..30L): DataSize {
        require(0 < range.first) { "0 이상이여야 합니다. ${range.first}" }
        val size = ThreadLocalRandom.current().nextLong(range.first, range.last + 1)
        return DataSize.ofMegabytes(size)
    }

    companion object : Log4j2Support
}

data class CreateDumpFile(
    val size: Long = 0,
    val delaySecond: Long = 0,
) {
    fun hasDelay(): Boolean = 0 < delaySecond

    fun toDataSize(): DataSize = DataSize.ofMegabytes(size)
}
