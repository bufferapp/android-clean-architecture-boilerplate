package org.buffer.android.boilerplate.domain.model

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by joebirch on 03/08/2017.
 */
interface BufferooRepository {

    fun clearBufferoos(): Completable

    fun saveBufferoos(bufferoos: List<Bufferoo>): Completable

    fun getBufferoos(): Single<List<Bufferoo>>

}