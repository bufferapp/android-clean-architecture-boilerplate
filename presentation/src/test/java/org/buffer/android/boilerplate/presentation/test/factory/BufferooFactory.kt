package org.buffer.android.boilerplate.presentation.test.factory

import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.presentation.model.BufferooView
import org.buffer.android.boilerplate.presentation.test.factory.DataFactory.Factory.randomLong
import org.buffer.android.boilerplate.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooList(count: Int): List<Bufferoo> {
            val bufferoos = mutableListOf<Bufferoo>()
            repeat(count) {
                bufferoos.add(makeBufferooModel())
            }
            return bufferoos
        }

        fun makeBufferooModel(): Bufferoo {
            return Bufferoo(randomLong(), randomUuid(), randomUuid(), randomUuid())
        }

        fun makeBufferooViewList(count: Int): List<BufferooView> {
            val bufferoos = mutableListOf<BufferooView>()
            repeat(count) {
                bufferoos.add(makeBufferooView())
            }
            return bufferoos
        }

        fun makeBufferooView(): BufferooView {
            return BufferooView(randomUuid(), randomUuid(), randomUuid())
        }

    }

}