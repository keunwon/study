package com.spring.sre.nginx

import com.spring.sre.nginx.util.getMegabytes
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import java.io.File
import java.util.*

@Service
class DumpFileService {

    fun createFileAndWriteDumpContent(dirPath: String, size: DataSize): File {
        return createDumpFile(dirPath)
            .apply { writeDumpContent(size) }
            .also { log.info("> 덤프 파일 생성 경로: ${it.path}, 용량: ${it.getMegabytes()}MB") }
    }

    fun deleteDumpFiles(dirPath: String): DeleteFilesDto {
        val fileInfoList = File(dirPath).listFiles()?.map { file ->
            FileInfo(file).also { file.deleteOnExit() }
        } ?: emptyList()
        return fileInfoList
            .run { DeleteFilesDto(dirPath, this) }
            .also { log.info("> 삭제 대상 파일 경로: ${it.rootPath}, 파일 개수: ${it.size}") }
    }

    private fun createDumpFile(dirPath: String): File {
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val path = "${dirPath}/${uuid}.txt"
        return File(path).apply { parentFile.mkdirs() }
    }

    private fun File.writeDumpContent(size: DataSize) {
        require(size.toBytes() < Int.MAX_VALUE) { "너무 큰 파일을 생성하려고 합니다. ${size.toMegabytes()}MB" }

        this.bufferedWriter().use { bw ->
            val sequence = generateSequence(0) { (it + 1) % 26 }.take(size.toBytes().toInt())
            val content = sequence.joinToString(separator = "") { ('a' + it).toString() }
            bw.write(content)
        }
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
    private val mb: Long,
) {
    constructor(file: File) : this(file.path, file.name, DataSize.ofBytes(file.length()).toMegabytes())
}
