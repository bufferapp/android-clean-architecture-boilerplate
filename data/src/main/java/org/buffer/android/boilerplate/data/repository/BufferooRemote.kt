package org.buffer.android.boilerplate.data.repository

import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity

/**
 * Interface defining methods for getting Bufferoos remotely. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */
interface BufferooRemote {

    /**
     * Retrieve a list of Bufferoos, from the network or any other remote
     */
    fun getBufferoos(): Single<List<BufferooEntity>>

}