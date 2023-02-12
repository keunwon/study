package com.github.keunwon.test

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
        val declaredFields = entity::class.java.declaredFields
        declaredFields.forEach { field ->
            if (field.isAnnotationPresent(Id::class.java) || field.isAnnotationPresent(EmbeddedId::class.java)) {
                return field.apply { isAccessible = true }
            }
        }
        throw IllegalArgumentException("id를 찾을 수 없습니다.")
    }
}
