package org.buffer.android.boilerplate.domain.test.factory

import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.test.factory.DataFactory.Factory.randomLong
import org.buffer.android.boilerplate.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferoo())
            }
            return bufferoos
        }

        fun makeBufferoo(): Bufferoo {
            return Bufferoo(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

    }

}