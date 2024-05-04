package com.jlegacyai.contentproviderjokesdatabase.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jlegacyai.contentproviderjokesdatabase.database.model.Category
import com.jlegacyai.contentproviderjokesdatabase.database.model.Flag
import com.jlegacyai.contentproviderjokesdatabase.database.model.Joke
import com.jlegacyai.contentproviderjokesdatabase.database.model.Language
import com.jlegacyai.contentproviderjokesdatabase.database.model.Type
import net.thauvin.erik.jokeapi.joke

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object{
        private val DB_NAME = "Joke Factory"
        private val DB_VERSION = 1
        private val TABLE_NAME = "Jokes"
        private val CATEGORY = "Category"
        private val JOKE = "Joke"
        private val ID = "ID"
        private val LANGUAGE = "Language"
        private val TYPE = "Type"
        private val SETUP = "Setup"
        private val DELIVERY = "Delivery"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $JOKE  Text, $SETUP Text, $DELIVERY Text, $LANGUAGE Text, $CATEGORY Text)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getAllJokes(): List<Joke> {
        val jokes = mutableListOf<Joke>()
        val db = writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val joke = Joke(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    Type.valueOf(cursor.getString(cursor.getColumnIndex(TYPE))),
                    Category.valueOf(cursor.getString(cursor.getColumnIndex(CATEGORY))),
                    cursor.getString(cursor.getColumnIndex(JOKE)),
                    cursor.getString(cursor.getColumnIndex(SETUP)),
                    cursor.getString(cursor.getColumnIndex(DELIVERY)),
                    Language.valueOf(cursor.getString(cursor.getColumnIndex(LANGUAGE)))
                )
                jokes.add(joke)
            }
            cursor.close()
        }

        db.close()
        return jokes
    }


    fun insertJoke(joke: Joke) {
        val db = writableDatabase

        // Create a ContentValues object to hold the joke data
        val values = ContentValues().apply {
            put(TYPE, joke.type.toString())
            put(CATEGORY, joke.category.toString())
            put(JOKE, joke.joke)
            put(SETUP, joke.setup)
            put(DELIVERY, joke.delivery)
            put(LANGUAGE, joke.lang.toString())
        }

        // Insert the joke into the database
        db.insert(TABLE_NAME, null, values)

        // Close the database connection
        db.close()
    }

}