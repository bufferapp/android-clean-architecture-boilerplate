package org.buffer.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooDataStore
import org.buffer.android.boilerplate.data.repository.BufferooRemote
import javax.inject.Inject

/**
 * Created by joebirch on 03/08/2017.
 */
open class BufferooRemoteDataStore @Inject constructor(private val bufferooRemote: BufferooRemote) :
        BufferooDataStore {

    override fun clearBufferoos(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getBufferoos(): Single<List<BufferooEntity>> {
        return bufferooRemote.getBufferoos()
    }

}