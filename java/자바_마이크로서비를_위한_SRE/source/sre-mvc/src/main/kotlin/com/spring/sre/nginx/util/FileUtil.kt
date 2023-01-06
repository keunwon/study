package com.spring.sre.nginx.util

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.unit.DataSize
import java.io.File

fun File.toResponseEntity(): ResponseEntity<Resource> {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .header(HttpHeaders.CONTENT_DISPOSITION, attachmentContentDispositionToString(name))
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(UrlResource(toURI()))
}

fun File.getMegabytes() = DataSize.ofBytes(length()).toMegabytes()

private fun attachmentContentDispositionToString(fileName: String): String =
    attachmentContentDisposition(fileName).toString()

private fun attachmentContentDisposition(fileName: String): ContentDisposition {
    return ContentDisposition.attachment()
        .filename(fileName)
        .build()
}
