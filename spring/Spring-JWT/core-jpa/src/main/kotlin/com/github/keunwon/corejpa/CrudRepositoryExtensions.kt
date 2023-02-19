package com.github.keunwon.corejpa

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun <T, ID> CrudRepository<T, ID>.getById(id: ID) = findByIdOrNull(id)
    ?: throw IllegalArgumentException("id를 찾을 수 없습니다. id: $id")
