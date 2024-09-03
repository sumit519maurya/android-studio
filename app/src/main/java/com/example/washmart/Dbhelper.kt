package com.example.washmart

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

data class User(
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val userid:String)

data class Bill(
val username: String,
val clothPrice: String?,
val serviceCharge: String?,
val totalCloths: String?,
val finalTotal: String?,
val orderDate: String?,
val selectedCloths: String?,  // Add selected cloths
val selectedFabric: String?,  // Add selected fabric
val selectedService: String?, // Add selected service
val userPhone: String?,       // Add user phone
val userAddress: String?      // Add user address
)

/*data class Bill1(
    val shirt: String?,
    val tshirt: String?,
    val pants: String?,
    val shorts: String?,
    val cotton: String?,
    val wool: String?,
    val silk: String?,
    val nylon: String?,
    val laundry: String?,
    val dry: String?,
    val iron: String?,
    val finalTotal: String?,
    val totalCloths: String?,
    val clothPrice: String?,
    val orderDate: String?,
    val serviceCharge: String?,
    val username: String?
)*/
class Dbhelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "washmart.db"
        private const val DATABASE_VERSION = 2

        private const val TABLE_USERS = "Users"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE_NUMBER = "phone_number"

        private const val TABLE_BILLS = "Bills"
        private const val COLUMN_BILL_ID = "bill_id"
        private const val COLUMN_USERNAMEb = "username"
        private const val COLUMN_CLOTH_PRICE = "cloth_price"
        private const val COLUMN_SERVICE_CHARGE = "service_charge"
        private const val COLUMN_TOTAL_CLOTHS = "total_cloths"
        private const val COLUMN_FINAL_TOTAL = "final_total"
        private const val COLUMN_ORDER_DATE = "order_date"
        private const val COLUMN_SELECTED_CLOTHS = "selected_cloths"
        private const val COLUMN_SELECTED_FABRIC = "selected_fabric"
        private const val COLUMN_SELECTED_SERVICE = "selected_service"
        private const val COLUMN_PHONENO = "userPhone"
        private const val COLUMN_ADDRESS = "Address"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USERNAME TEXT UNIQUE NOT NULL, "
                + "$COLUMN_PASSWORD TEXT NOT NULL, "
                + "$COLUMN_EMAIL TEXT UNIQUE NOT NULL, "
                + "$COLUMN_PHONE_NUMBER TEXT NOT NULL)")

        db.execSQL("DROP TABLE IF EXISTS $TABLE_BILLS")
        val createBillTable = ("CREATE TABLE $TABLE_BILLS ("
                + "$COLUMN_BILL_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USERNAMEb TEXT NOT NULL, "
                + "$COLUMN_CLOTH_PRICE TEXT, "
                + "$COLUMN_SERVICE_CHARGE TEXT, "
                + "$COLUMN_TOTAL_CLOTHS TEXT, "
                + "$COLUMN_FINAL_TOTAL TEXT, "
                + "$COLUMN_ORDER_DATE TEXT, "
                + "$COLUMN_SELECTED_CLOTHS TEXT, "
                + "$COLUMN_SELECTED_FABRIC TEXT, "
                + "$COLUMN_SELECTED_SERVICE TEXT, "
                + "$COLUMN_PHONENO TEXT, "
                + "$COLUMN_ADDRESS TEXT, "
                + "$COLUMN_USER_ID INTEGER, "
                + "FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_USER_ID))"
                )
        db.execSQL(createTable)
        db.execSQL(createBillTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BILLS")
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
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(username, password)
        )
        val isUserExists = cursor.count > 0
        cursor.close()
        db.close()
        return isUserExists
    }

    fun deleteUser(username: String): Boolean {
        val db = this.writableDatabase
        val whereClause = "$COLUMN_USERNAME = ?"
        val whereArgs = arrayOf(username)

        val result = db.delete(TABLE_USERS, whereClause, whereArgs)
        db.close()

        return result > 0
    }

    @SuppressLint("Range")
    fun getUser(username: String): User {
        val db = this.readableDatabase
        val query = "SELECT * FROM users WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        if (cursor.moveToFirst()) {
            val userid = cursor.getString(cursor.getColumnIndex("user_id"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"))


            cursor.close()
            return User(username, password, email, phoneNumber,userid)
        }

        cursor.close()
        throw NoSuchElementException("No user found with username: $username")
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

    fun saveBill(
        username: String,
        userId: Long,
        clothPrice: String?,
        serviceCharge: String?,
        totalCloths: String?,
        finalTotal: String?,
        orderDate: String?,
        userPhone: String?,
        userAddress: String?,
        shirt: Boolean,   // Boolean for checkbox
        tshirt: Boolean,
        pants: Boolean,
        shorts: Boolean,
        cotton: Boolean,
        wool: Boolean,
        silk: Boolean,
        nylon: Boolean,
        laundry: Boolean,
        dry: Boolean,
        iron: Boolean,
    ): Boolean {
        val db = this.writableDatabase

        // Combine selected clothes into a single string
        val selectedCloths = mutableListOf<String>()
        if (shirt) selectedCloths.add("Shirt")
        if (tshirt) selectedCloths.add("Tshirt")
        if (pants) selectedCloths.add("Pants")
        if (shorts) selectedCloths.add("Shorts")

        // Combine selected fabrics into a single string
        val selectedFabric = mutableListOf<String>()
        if (cotton) selectedFabric.add("Cotton")
        if (wool) selectedFabric.add("Wool")
        if (silk) selectedFabric.add("Silk")
        if (nylon) selectedFabric.add("Nylon")

        // Combine selected services into a single string
        val selectedService = mutableListOf<String>()
        if (laundry) selectedService.add("Laundry")
        if (dry) selectedService.add("Dry")
        if (iron) selectedService.add("Iron")

        // Convert the lists to comma-separated strings
        val selectedClothsString = selectedCloths.joinToString(", ")
        val selectedFabricString = selectedFabric.joinToString(", ")
        val selectedServiceString = selectedService.joinToString(", ")

        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAMEb, username)
            put(COLUMN_USER_ID, userId)
            put(COLUMN_CLOTH_PRICE, clothPrice)
            put(COLUMN_SERVICE_CHARGE, serviceCharge)
            put(COLUMN_TOTAL_CLOTHS, totalCloths)
            put(COLUMN_FINAL_TOTAL, finalTotal)
            put(COLUMN_ORDER_DATE, orderDate)
            put(COLUMN_PHONENO, userPhone)
            put(COLUMN_ADDRESS, userAddress)
            put(COLUMN_SELECTED_CLOTHS, selectedClothsString)  // Store selected cloths
            put(COLUMN_SELECTED_FABRIC, selectedFabricString)   // Store selected fabric
            put(COLUMN_SELECTED_SERVICE, selectedServiceString)
            if (userId != null) {
                put(COLUMN_USER_ID, userId)  // Only add if userId is not null
            }
        }

        val result = db.insert(TABLE_BILLS, null, contentValues)
        db.close()

        return result != -1L
    }
    @SuppressLint("Range")
    fun getBillsForUser(userId: Long): List<Bill> {
        val db = this.readableDatabase
       val bills = mutableListOf<Bill>()

        // Fetch user ID based on username
       /* val userIdQuery = "SELECT $COLUMN_USER_ID FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ?"
        val userIdCursor: Cursor = db.rawQuery(userIdQuery, arrayOf(username))

        val userId: Int? = if (userIdCursor.moveToFirst()) {
            userIdCursor.getInt(userIdCursor.getColumnIndexOrThrow(COLUMN_USER_ID))
        } else {
            userIdCursor.close()
            db.close()
            Log.d("DbHelper", "No user found for username: $username")
            return emptyList()
        }
        userIdCursor.close()*/

        // Fetch bills based on user ID
        val query = "SELECT * FROM $TABLE_BILLS WHERE $COLUMN_USER_ID = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(userId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val bill = Bill(
                    username =  cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAMEb)), // Use the provided username
                    clothPrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLOTH_PRICE)),
                    serviceCharge = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_CHARGE)),
                    totalCloths = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_CLOTHS)),
                    finalTotal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FINAL_TOTAL)),
                    orderDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ORDER_DATE)),
                    selectedCloths = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SELECTED_CLOTHS)),
                    selectedFabric = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SELECTED_FABRIC)),
                    selectedService = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SELECTED_SERVICE)),
                    userPhone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONENO)),
                    userAddress = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))
                )
                bills.add(bill)
            } while (cursor.moveToNext())
        } else {
            Log.d("DbHelper", "No bills found for user ID: $userId")
        }

        cursor.close()
        db.close()
        return bills
    }

}









