package org.buffer.android.boilerplate.data

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.mapper.BufferooMapper
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooDataStore
import org.buffer.android.boilerplate.data.source.BufferooCacheDataStore
import org.buffer.android.boilerplate.data.source.BufferooDataStoreFactory
import org.buffer.android.boilerplate.data.source.BufferooRemoteDataStore
import org.buffer.android.boilerplate.data.test.factory.BufferooFactory
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BufferooDataRepositoryTest {

    private lateinit var bufferooDataRepository: BufferooDataRepository

    private lateinit var bufferooDataStoreFactory: BufferooDataStoreFactory
    private lateinit var bufferooMapper: BufferooMapper
    private lateinit var bufferooCacheDataStore: BufferooCacheDataStore
    private lateinit var bufferooRemoteDataStore: BufferooRemoteDataStore

    @Before
    fun setUp() {
        bufferooDataStoreFactory = mock()
        bufferooMapper = mock()
        bufferooCacheDataStore = mock()
        bufferooRemoteDataStore = mock()
        bufferooDataRepository = BufferooDataRepository(bufferooDataStoreFactory, bufferooMapper)
        stubBufferooDataStoreFactoryRetrieveCacheDataStore()
        stubBufferooDataStoreFactoryRetrieveRemoteDataStore()
    }

    //<editor-fold desc="Clear Bufferoos">
    @Test
    fun clearBufferoosCompletes() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.clearBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearBufferoosCallsCacheDataStore() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearBufferoos().test()
        verify(bufferooCacheDataStore).clearBufferoos()
    }

    @Test
    fun clearBufferoosNeverCallsRemoteDataStore() {
        stubBufferooCacheClearBufferoos(Completable.complete())
        bufferooDataRepository.clearBufferoos().test()
        verify(bufferooRemoteDataStore, never()).clearBufferoos()
    }
    //</editor-fold>

    //<editor-fold desc="Save Bufferoos">
    @Test
    fun saveBufferoosCompletes() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.saveBufferoos(
                BufferooFactory.makeBufferooList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBufferoosCallsCacheDataStore() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(bufferooCacheDataStore).saveBufferoos(any())
    }

    @Test
    fun saveBufferoosNeverCallsRemoteDataStore() {
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(bufferooRemoteDataStore, never()).saveBufferoos(any())
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheDataStoreIsCached(Single.just(true))
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        stubBufferooCacheDataStoreGetBufferoos(Flowable.just(
                BufferooFactory.makeBufferooEntityList(2)))
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val testObserver = bufferooDataRepository.getBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnsData() {
        stubBufferooCacheDataStoreIsCached(Single.just(true))
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        val bufferoos = BufferooFactory.makeBufferooList(2)
        val bufferooEntities = BufferooFactory.makeBufferooEntityList(2)
        bufferoos.forEachIndexed { index, bufferoo ->
            stubBufferooMapperMapFromEntity(bufferooEntities[index], bufferoo) }
        stubBufferooCacheDataStoreGetBufferoos(Flowable.just(bufferooEntities))

        val testObserver = bufferooDataRepository.getBufferoos().test()
        testObserver.assertValue(bufferoos)
    }

    @Test
    fun getBufferoosSavesBufferoosWhenFromCacheDataStore() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooCacheDataStore)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(bufferooCacheDataStore).saveBufferoos(any())
    }

    @Test
    fun getBufferoosNeverSavesBufferoosWhenFromRemoteDataStore() {
        stubBufferooDataStoreFactoryRetrieveDataStore(bufferooRemoteDataStore)
        stubBufferooCacheSaveBufferoos(Completable.complete())
        bufferooDataRepository.saveBufferoos(BufferooFactory.makeBufferooList(2)).test()
        verify(bufferooRemoteDataStore, never()).saveBufferoos(any())
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheSaveBufferoos(completable: Completable) {
        whenever(bufferooCacheDataStore.saveBufferoos(any()))
                .thenReturn(completable)
    }

    private fun stubBufferooCacheDataStoreIsCached(single: Single<Boolean>) {
        whenever(bufferooCacheDataStore.isCached())
                .thenReturn(single)
    }

    private fun stubBufferooCacheDataStoreGetBufferoos(single: Flowable<List<BufferooEntity>>) {
        whenever(bufferooCacheDataStore.getBufferoos())
                .thenReturn(single)
    }

    private fun stubBufferooRemoteDataStoreGetBufferoos(single: Flowable<List<BufferooEntity>>) {
        whenever(bufferooRemoteDataStore.getBufferoos())
                .thenReturn(single)
    }

    private fun stubBufferooCacheClearBufferoos(completable: Completable) {
        whenever(bufferooCacheDataStore.clearBufferoos())
                .thenReturn(completable)
    }

    private fun stubBufferooDataStoreFactoryRetrieveCacheDataStore() {
        whenever(bufferooDataStoreFactory.retrieveCacheDataStore())
                .thenReturn(bufferooCacheDataStore)
    }

    private fun stubBufferooDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(bufferooDataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(bufferooCacheDataStore)
    }

    private fun stubBufferooDataStoreFactoryRetrieveDataStore(dataStore: BufferooDataStore) {
        whenever(bufferooDataStoreFactory.retrieveDataStore(any()))
                .thenReturn(dataStore)
    }

    private fun stubBufferooMapperMapFromEntity(bufferooEntity: BufferooEntity,
                                                bufferoo: Bufferoo) {
        whenever(bufferooMapper.mapFromEntity(bufferooEntity))
                .thenReturn(bufferoo)
    }
    //</editor-fold>

}