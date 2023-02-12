package com.github.keunwon.repository

import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import java.util.*
import javax.transaction.NotSupportedException

open class InmemoryCrudRepository<T : Any, ID>(
    private val primaryKeyGenerator: PrimaryKeyGenerator<ID>,
) : CrudRepository<T, ID> where ID : Serializable, ID : Comparable<ID> {
    private val entities = mutableMapOf<ID, T>()
    private val entityReflection = EntityReflection<T, ID>()

    override fun <S : T> save(entity: S): S {
        val key = entityReflection.maxPrimaryKey(entities) ?: entityReflection.getId(entity)
        val nextKey = primaryKeyGenerator.nextPrimaryKey(key)
        entityReflection.setId(entity, nextKey)
        entities[nextKey] = entity
        return entity
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        return entities.onEach { save(it) }
    }

    override fun findById(id: ID): Optional<T> {
        return Optional.ofNullable(entities[id])
    }

    override fun existsById(id: ID): Boolean {
        return entities.containsKey(id)
    }

    override fun findAll(): MutableIterable<T> {
        return entities.values
    }

    override fun findAllById(ids: MutableIterable<ID>): MutableIterable<T> {
        return entities.filterKeys { ids.contains(it) }.values.toMutableList()
    }

    override fun count(): Long {
        return entities.count().toLong()
    }

    override fun deleteById(id: ID) {
        entities.remove(id)
    }

    override fun delete(entity: T) {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteAllById(ids: MutableIterable<ID>) {
        ids.forEach { deleteById(it) }
    }

    override fun deleteAll(entities: MutableIterable<T>) {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteAll() {
        entities.clear()
    }

    companion object {
        private const val NOT_SUPPORTED_EXCEPTION_MESSAGE = "지원하지 않는 메서드 입니다."
    }
}
