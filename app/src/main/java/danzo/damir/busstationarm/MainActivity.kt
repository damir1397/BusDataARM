package danzo.damir.busstationarm

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var  dataBase:DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dataBase= DataBase(this)

        add.setOnClickListener {
            val db: SQLiteDatabase = dataBase.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBase.NAME_STOP,name_stop.text.toString())
            contentValues.put(DataBase.ROUTE,route.text.toString())
            contentValues.put(DataBase.MOVEMENT_TIME_TO_STOP,movement_time_to_stop.text.toString())
            contentValues.put(DataBase.NUMBER_OF_SEATSH,number_of_seats.text.toString())
            contentValues.put(DataBase.TICKET_PRICE,ticket_price.text.toString())
            contentValues.put(DataBase.NUMBER_FLIGHTS_DAY,number_flights_day.text.toString())
            db.insert(DataBase.TABLE_BUS,null,contentValues)

        }

        delet.setOnClickListener {}
dataBase.close()
    }
}
