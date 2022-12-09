package com.spring.sre.chapter02.util

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.io.File

fun File.toResponseEntity(): ResponseEntity<Resource> {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .header(HttpHeaders.CONTENT_DISPOSITION, attachmentContentDisposition(this.name).toString())
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(UrlResource(this.toURI()))
}

private fun attachmentContentDisposition(fileName: String): ContentDisposition {
    return ContentDisposition.attachment()
        .filename(fileName)
        .build()
}

