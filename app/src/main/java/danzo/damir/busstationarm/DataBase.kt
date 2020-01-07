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
                    + KEY_ID + " integer primary key,"   //id
                    + ROUTE1 + " text,"                  //начало остановки
                    + ROUTE2 + " text,"                  //конец остановки
                    + MOVEMENT_TIME_TO_STOP + " text,"   //время до останоки
                    + NUMBER_OF_SEATS + " text,"         //количество мест
                    + TICKET_PRICE + " text,"            //цена
                    + NUMBER_FLIGHTS_DAY + " text" + ")" //сколько рейсов день
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
        const val DATABASE_VERSION: Int = 1
        const val DATABASE_NAME: String = "contactDb"
        const val TABLE_BUS: String = "Contacts"
        const val KEY_ID: String = "id"
        const val ROUTE1: String = "nameOFStop"
        const val ROUTE2: String = "route"
        const val MOVEMENT_TIME_TO_STOP: String = "movementTimeToStop"
        const val NUMBER_OF_SEATS: String = "numberSeats"
        const val TICKET_PRICE: String = "ticketPrice"
        const val NUMBER_FLIGHTS_DAY: String = "numberFlightsDay"

    }
}