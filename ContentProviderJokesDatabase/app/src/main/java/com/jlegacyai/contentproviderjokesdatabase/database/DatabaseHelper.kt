package com.jlegacyai.contentproviderjokesdatabase.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import androidx.sqlite.SQLiteException
import com.jlegacyai.contentproviderjokesdatabase.database.model.Category
import com.jlegacyai.contentproviderjokesdatabase.database.model.Joke
import com.jlegacyai.contentproviderjokesdatabase.database.model.Language
import com.jlegacyai.contentproviderjokesdatabase.database.model.Type

class DatabaseHelper(val context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

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

        private var instance: DatabaseHelper? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $JOKE  Text, $TYPE text ,$SETUP Text, $DELIVERY Text, $LANGUAGE Text, $CATEGORY Text)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
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
        try {
            val values = ContentValues().apply {
                put(TYPE, joke.type.toString())
                put(CATEGORY, joke.category.toString())
                put(JOKE, joke.joke)
                put(SETUP, joke.setup)
                put(DELIVERY, joke.delivery)
                put(LANGUAGE, joke.lang.toString())
            }

            val result = db.insert(TABLE_NAME, null, values)
            if (result == -1L) {
                Log.e("DatabaseHelper", "Failed to insert joke")
                Toast.makeText(context, "Joke Failed to Insert into DB", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Joke Added Successfully to DB", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SQLiteException) {
            Log.e("DatabaseHelper", "Error inserting joke", e)
        } finally {
            db.close()
        }
    }


}