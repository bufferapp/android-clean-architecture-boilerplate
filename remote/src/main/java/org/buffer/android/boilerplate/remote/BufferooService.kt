package org.buffer.android.boilerplate.remote

import io.reactivex.Observable
import org.buffer.android.boilerplate.remote.model.BufferooModel
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Bufferoo API
 */
interface BufferooService {

    @GET("team.json")
    fun getBufferoos(): Observable<BufferooResponse>

    class BufferooResponse {
        lateinit var team: List<BufferooModel>
    }

}