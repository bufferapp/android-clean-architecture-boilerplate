package org.buffer.android.boilerplate.data

import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.mapper.BufferooMapper
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.source.BufferooDataStoreFactory
import org.buffer.android.boilerplate.data.source.BufferooRemoteDataStore
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [BufferooRepository] interface for communicating to and from
 * data sources
 */
class BufferooDataRepository @Inject constructor(private val factory: BufferooDataStoreFactory,
                                                 private val bufferooMapper: BufferooMapper):
        BufferooRepository {

    override fun clearBufferoos(): Completable {
        return factory.retrieveCacheDataStore().clearBufferoos()
    }

    override fun saveBufferoos(bufferoos: List<Bufferoo>): Completable {
        val bufferooEntities = mutableListOf<BufferooEntity>()
        bufferoos.map { bufferooEntities.add(bufferooMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveBufferoos(bufferooEntities)
    }

    override fun getBufferoos(): Single<List<Bufferoo>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.getBufferoos()
                .flatMap {
                    val bufferoos = mutableListOf<Bufferoo>()
                    it.map { bufferoos.add(bufferooMapper.mapFromEntity(it)) }
                    Single.just(bufferoos)
                }
                .flatMap {
                    if (dataStore is BufferooRemoteDataStore) {
                        saveBufferoos(it).toSingle { it }
                    } else {
                        Single.just(it)
                    }
                }
    }

}