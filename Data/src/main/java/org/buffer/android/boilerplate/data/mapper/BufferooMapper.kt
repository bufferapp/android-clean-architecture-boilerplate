package org.buffer.android.boilerplate.data.mapper

import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.domain.model.Bufferoo


/**
 * Map a [BufferooEntity] to and froma a [Bufferoo] instance when data is moving between
 * this later and the Domain layer
 */
class BufferooMapper : Mapper<BufferooEntity, Bufferoo> {

    /**
     * Map a [BufferooEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: BufferooEntity): Bufferoo {
        return Bufferoo(type.name, type.title, type.avatar)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Bufferoo): BufferooEntity {
        return BufferooEntity(type.name, type.title, type.avatar)
    }


}