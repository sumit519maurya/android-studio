package com.example.washmart

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Dbhelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "washmart.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "Users"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE_NUMBER = "phone_number"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USERNAME TEXT UNIQUE NOT NULL, "
                + "$COLUMN_PASSWORD TEXT NOT NULL, "
                + "$COLUMN_EMAIL TEXT UNIQUE NOT NULL, "
                + "$COLUMN_PHONE_NUMBER TEXT NOT NULL)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }
    fun addUser(username: String, password: String, email: String, phoneNumber: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PHONE_NUMBER, phoneNumber)
        }
        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return result != -1L
    }
    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?", arrayOf(username, password))
        val isUserExists = cursor.count > 0
        cursor.close()
        db.close()
        return isUserExists
    }

    @SuppressLint("Range")
    fun getAllUsers(): List<String> {
        val users = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_USERS", null)

        if (cursor.moveToFirst()) {
            do {
                val username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
                users.add(username)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return users
    }
}