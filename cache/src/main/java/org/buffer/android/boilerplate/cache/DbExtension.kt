package org.buffer.android.boilerplate.cache

import android.database.sqlite.SQLiteDatabase

inline fun <T> SQLiteDatabase.inTransaction(action: SQLiteDatabase.() -> T) {
    beginTransaction()
    try {
        action()
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}
