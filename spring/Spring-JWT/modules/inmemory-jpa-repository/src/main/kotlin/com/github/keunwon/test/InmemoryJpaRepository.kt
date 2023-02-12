package com.github.keunwon.test

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.FluentQuery
import java.io.Serializable
import java.util.*
import java.util.function.Function
import javax.transaction.NotSupportedException

open class InmemoryJpaRepository<T : Any, ID>(
    private val primaryKeyGenerator: PrimaryKeyGenerator<ID>,
) : JpaRepository<T, ID> where ID : Serializable, ID : Comparable<ID> {
    private val entities: MutableMap<ID, T> = mutableMapOf()
    private val entityIdReflection = EntityReflection<T, ID>()

    override fun <S : T> save(entity: S): S {
        val key = entityIdReflection.maxPrimaryKey(entities) ?: entityIdReflection.getId(entity)
        val nextKey = primaryKeyGenerator.nextPrimaryKey(key)
        entityIdReflection.setId(entity, nextKey)
        entities[nextKey] = entity
        return entity
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): MutableList<S> {
        return entities.map { save(it) }.toMutableList()
    }

    override fun findById(id: ID): Optional<T> {
        return Optional.ofNullable(entities[id])
    }

    override fun existsById(id: ID): Boolean {
        return entities.containsKey(id)
    }

    override fun findAll(): MutableList<T> {
        return entities.values.toMutableList()
    }

    override fun findAll(sort: Sort): MutableList<T> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> findAll(example: Example<S>): MutableList<S> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun findAll(pageable: Pageable): Page<T> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun findAllById(ids: MutableIterable<ID>): MutableList<T> {
        return entities.filterKeys { ids.contains(it) }.values.toMutableList()
    }

    override fun count(): Long {
        return entities.count().toLong()
    }

    override fun <S : T> count(example: Example<S>): Long {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteById(id: ID) {
        entities.remove(id)
    }

    override fun delete(entity: T) {
        val id = entityIdReflection.getId(entity)
        deleteById(id)
    }

    override fun deleteAllById(ids: MutableIterable<ID>) {
        val keys = entities.filterKeys { ids.contains(it) }.keys
        keys.forEach { deleteById(it) }
    }

    override fun deleteAll(entities: MutableIterable<T>) {
        entities.forEach { delete(it) }
    }

    override fun deleteAll() {
        entities.clear()
    }

    override fun <S : T> findOne(example: Example<S>): Optional<S> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> exists(example: Example<S>): Boolean {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun flush() {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> saveAndFlush(entity: S): S {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun <S : T> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteAllInBatch(entities: MutableIterable<T>) {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteAllInBatch() {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<ID>) {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    @Deprecated("Deprecated in Java")
    override fun getOne(id: ID): T {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    @Deprecated("Deprecated in Java")
    override fun getById(id: ID): T {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    override fun getReferenceById(id: ID): T {
        throw NotSupportedException(NOT_SUPPORTED_EXCEPTION_MESSAGE)
    }

    companion object {
        private const val NOT_SUPPORTED_EXCEPTION_MESSAGE = "지원하지 않는 메서드 입니다."
    }
}
