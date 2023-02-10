package com.keunwon.auth

import org.springframework.data.repository.CrudRepository
import java.util.*

abstract class InmemoryRepository<T : Any> : CrudRepository<T, Long> {
    private val result: MutableMap<Long, T> = mutableMapOf()
    private var id: Long = 0

    override fun <S : T> save(entity: S): S {
        result[++id] = entity
        return entity
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach { save(it) }
        return entities
    }

    override fun findById(id: Long): Optional<T> {
        return Optional.ofNullable(result[id])
    }

    override fun findAll(): MutableIterable<T> {
        return result.values
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<T> {
        return result.filterKeys { ids.contains(it) }.values.toMutableList()
    }

    override fun existsById(id: Long): Boolean {
        return result.containsKey(id)
    }

    override fun count(): Long {
        return result.count().toLong()
    }

    override fun deleteById(id: Long) {
        result.remove(id)
    }

    override fun delete(entity: T) {
        val key = result.asSequence().find { it.value == entity }?.key
        if (key != null) result.remove(key)
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        ids.forEach { deleteById(it) }
    }

    override fun deleteAll(entities: MutableIterable<T>) {
        entities.forEach { delete(it) }
    }

    override fun deleteAll() {
        result.clear()
    }
}
