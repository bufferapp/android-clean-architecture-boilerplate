package org.buffer.android.boilerplate.cache.db.constants

import org.buffer.android.boilerplate.cache.db.Db

/**
 * Defines DB queries for the Bufferoos Table
 */
object BufferooConstants {

    internal val QUERY_GET_ALL_BUFFEROOS = "SELECT * FROM " + Db.BufferooTable.TABLE_NAME

}