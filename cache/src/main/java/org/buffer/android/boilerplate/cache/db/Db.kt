package org.buffer.android.boilerplate.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object BufferooTable {
        const val TABLE_NAME = "bufferroos"

        const val BUFFEROO_ID = "bufferoo_id"
        const val NAME = "name"
        const val TITLE = "title"
        const val AVATAR = "avatar"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        BUFFEROO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        NAME + " TEXT NOT NULL," +
                        TITLE + " TEXT," +
                        AVATAR + " TEXT" +
                        "); "
    }

}