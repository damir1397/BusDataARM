package danzo.damir.busstationarm

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*


class SearchDataActivity : AppCompatActivity() {

    lateinit var dataBase: DataBase

    lateinit var route1List: ArrayList<String>
    lateinit var route2List: ArrayList<String>
    lateinit var movementStopList: ArrayList<String>
    lateinit var numberSeatsList: ArrayList<String>
    lateinit var ticketPriceList: ArrayList<String>
    lateinit var numberFlightsDayList: ArrayList<String>
//    lateinit var column1:String
//    lateinit var column2:String
//    lateinit var column3:String
//    lateinit var column4 :String
//    lateinit var column5 :String
//    lateinit var column6 :String

    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        route1List = ArrayList()
        route2List = ArrayList()
        movementStopList = ArrayList()
        numberSeatsList = ArrayList()
        ticketPriceList = ArrayList()
        numberFlightsDayList = ArrayList()

        dataBase = DataBase(this)
        back_button.setOnClickListener {
            finish()
            startActivity(Intent(this, MenuActivity::class.java))
        }

        db = dataBase.writableDatabase
        val cursor: Cursor = db.query(DataBase.TABLE_BUS, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            val route1: Int = cursor.getColumnIndex(DataBase.ROUTE1)
            val route2: Int = cursor.getColumnIndex(DataBase.ROUTE2)
            val movementStop: Int = cursor.getColumnIndex(DataBase.MOVEMENT_TIME_TO_STOP)
            val numberSeats: Int = cursor.getColumnIndex(DataBase.NUMBER_OF_SEATS)
            val ticketPrice: Int = cursor.getColumnIndex(DataBase.TICKET_PRICE)
            val numberFlightsDay: Int = cursor.getColumnIndex(DataBase.NUMBER_FLIGHTS_DAY)

            do {
                route1List.add(cursor.getString(route1).toString())
                route2List.add(cursor.getString(route2).toString())
                movementStopList.add(cursor.getString(movementStop).toString())
                numberSeatsList.add(cursor.getString(numberSeats).toString())
                ticketPriceList.add(cursor.getString(ticketPrice).toString())
                numberFlightsDayList.add(cursor.getString(numberFlightsDay).toString())


            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")

        runOnUiThread {
        prise.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line, ticketPriceList as List<Any?>
            )
        )
        time.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line, movementStopList as List<Any?>
            )
        )
        route1.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line, route1List as List<Any?>
            )
        )
        route2.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line, route2List as List<Any?>
            )
        )}

        search_button.setOnClickListener {
            val db: SQLiteDatabase = dataBase.readableDatabase
            val select = db.rawQuery(
                "SELECT * FROM ${DataBase.TABLE_BUS} WHERE ${DataBase.ROUTE1} ='${route1.text}' or ${DataBase.ROUTE2} ='${route2.text}'or ${DataBase.TICKET_PRICE} ='${prise.text}'or ${DataBase.MOVEMENT_TIME_TO_STOP} ='${time.text}' ",
                null)
            select.moveToFirst()
            if (!select.isAfterLast) {
                do { // Passing values
                    val column1 = select.getString(0)
                    val column2 = select.getString(1)
                    val column3 = select.getString(2)
                    val column4 = select.getString(3)
                    val column5 = select.getString(4)
                    val column6 = select.getString(5)

                    println(column1)
                    println(column2)
                    println(column3)
                    println(column4)
                    println(column5)
                    println(column6)

                    // Do something Here with values
                } while (select.moveToNext())
            }
            select.close()
            db.close()
        }
        cursor.close()

    }
}
