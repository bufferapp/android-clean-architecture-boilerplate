package org.buffer.android.boilerplate.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import org.buffer.android.boilerplate.cache.db.Db
import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import javax.inject.Inject

/**
 * Maps a [CachedBufferoo] instance to a database entity.
 */
class BufferooMapper @Inject constructor(): ModelDbMapper<CachedBufferoo> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedBufferoo]
     */
    override fun toContentValues(model: CachedBufferoo): ContentValues {
        val values = ContentValues()
        values.put(Db.BufferooTable.NAME, model.name)
        values.put(Db.BufferooTable.TITLE, model.title)
        values.put(Db.BufferooTable.AVATAR, model.avatar)
        return values
    }

    /**
     * Parse the cursor creating a [CachedBufferoo] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedBufferoo {
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Db.BufferooTable.NAME))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.BufferooTable.TITLE))
        val avatar = cursor.getString(cursor.getColumnIndexOrThrow(Db.BufferooTable.AVATAR))
        return CachedBufferoo(name, title, avatar)
    }

}