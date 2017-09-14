package org.buffer.android.boilerplate.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.buffer.android.boilerplate.cache.db.constants.BufferooConstants

/**
 * Model used solely for the caching of a bufferroo
 */
@Entity(tableName = BufferooConstants.TABLE_NAME)
data class CachedBufferoo(

        @PrimaryKey
        var id: Long,
        val name: String,
        val title: String,
        val avatar: String

)