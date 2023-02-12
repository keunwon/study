package com.github.keunwon.repository

import java.io.Serializable
import java.lang.reflect.Field
import javax.persistence.EmbeddedId
import javax.persistence.Id


class EntityReflection<T : Any, ID> where ID : Serializable, ID : Comparable<ID> {
    @Suppress("UNCHECKED_CAST")
    fun getId(entity: T) = getFieldId(entity).get(entity) as ID

    fun setId(entity: T, id: ID) = getFieldId(entity).set(entity, id)

    fun maxPrimaryKey(entities: MutableMap<ID, T>): ID? {
        return entities.maxOfOrNull { getId(it.value) }
    }

    private fun getFieldId(entity: T): Field {
        var clazz: Class<*>? = entity.javaClass
        while (clazz != null) {
            val declaredFields = clazz.declaredFields
            declaredFields.forEach { field ->
                if (hasEntityId(field)) return field.apply { isAccessible = true }
            }
            clazz = clazz.superclass
        }
        throw IllegalArgumentException("id를 찾을 수 없습니다.")
    }

    private fun hasEntityId(field: Field): Boolean {
        return field.isAnnotationPresent(Id::class.java) ||
                field.isAnnotationPresent(EmbeddedId::class.java)
    }
}
