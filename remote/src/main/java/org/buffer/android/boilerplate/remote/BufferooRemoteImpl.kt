package org.buffer.android.boilerplate.remote

import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooRemote
import org.buffer.android.boilerplate.remote.mapper.BufferooEntityMapper
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooRemoteImpl @Inject constructor(private val bufferooService: BufferooService,
                                             private val entityMapper: BufferooEntityMapper) :
        BufferooRemote {

    /**
     * Retrieve a list of [BufferooEntity] instances from the [BufferooService].
     */
    override fun getBufferoos(): Single<List<BufferooEntity>> {
        return bufferooService.getBufferoos()
                .map {
                    it.team.map { listItem ->
                        entityMapper.mapFromRemote(listItem)
                    }
                }
    }

}