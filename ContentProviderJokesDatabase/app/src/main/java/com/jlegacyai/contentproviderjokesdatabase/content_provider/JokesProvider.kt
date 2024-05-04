package com.jlegacyai.contentproviderjokesdatabase.content_provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.jlegacyai.contentproviderjokesdatabase.database.DatabaseHelper

class JokesProvider: ContentProvider() {

    private lateinit var dbHelper: DatabaseHelper

    companion object {
        private const val JOKES = 100
        private const val JOKE_ID = 101
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI("com.jlegacyai.contentproviderjokesdatabase.jokesprovider", "jokes", JOKES)
            addURI("com.jlegacyai.contentproviderjokesdatabase.jokesprovider", "jokes/#", JOKE_ID)
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            JOKES -> "vnd.android.cursor.dir/vnd.com.jlegacyai.jokes"
            JOKE_ID -> "vnd.android.cursor.item/vnd.com.jlegacyai.jokes"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }



    override fun onCreate(): Boolean {
        dbHelper = DatabaseHelper(context ?: throw IllegalStateException("Context cannot be null"))
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Jokes", projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val id = db.insert("Jokes", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val count = db.delete("Jokes", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val count = db.update("Jokes", values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}