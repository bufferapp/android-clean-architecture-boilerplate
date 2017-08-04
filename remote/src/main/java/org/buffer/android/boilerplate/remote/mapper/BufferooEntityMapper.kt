package org.buffer.android.boilerplate.remote.mapper

import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.remote.model.BufferooModel

/**
 * Map a [BufferooModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class BufferooEntityMapper : EntityMapper<BufferooModel, BufferooEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [BufferooEntity] model
     */
    override fun mapFromRemote(type: BufferooModel): BufferooEntity {
        return BufferooEntity(type.name, type.title, type.avatar)
    }

}