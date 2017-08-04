package org.buffer.android.boilerplate.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor

/**
 * Interface for database model mappers. It provides helper methods that facilitate
 * saving/retrieving java models into/from a relational database.
 *
 * @param <T> the model type
 */
interface ModelDbMapper<T> {

    /**
     * Converts the model into ContentValues for the database table
     */
    fun toContentValues(model: T): ContentValues

    /**
     * Parses the Cursor resulting from a database query into the java model
     */
    fun parseCursor(cursor: Cursor): T

}