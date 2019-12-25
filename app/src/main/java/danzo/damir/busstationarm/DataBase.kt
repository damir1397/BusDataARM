package danzo.damir.busstationarm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + TABLE_BUS + "("
                    + KEY_ID + " integer primary key,"
                    + NAME_STOP + " text,"
                    + ROUTE + " text"
                    + MOVEMENT_TIME_TO_STOP + " text"
                    + NUMBER_OF_SEATSH + " text"
                    + TICKET_PRICE + " text"
                    + NUMBER_FLIGHTS_DAY + " text" + ")"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("drop table if exists $TABLE_BUS")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "contactDb"
        const val TABLE_BUS = "contacts"
        const val KEY_ID = "_id"
        const val NAME_STOP: String = "nameOFStop"
        const val ROUTE: String = "route"
        const val MOVEMENT_TIME_TO_STOP = "movement_time_to_stop"
        const val NUMBER_OF_SEATSH = "number_of_seats"
        const val TICKET_PRICE = "ticket_price"
        const val NUMBER_FLIGHTS_DAY = "number_flights_day"

    }
}