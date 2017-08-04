package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity

/**
 * Created by joebirch on 03/08/2017.
 */
interface BufferooDataStore {

    fun clearBufferoos(): Completable

    fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable

    fun getBufferoos(): Single<List<BufferooEntity>>

}