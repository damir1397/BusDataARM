package danzo.damir.busstationarm

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_data.*


class AddDataActivity : AppCompatActivity() {

    lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        back_add_data.setOnClickListener {
            finish()
            startActivity(Intent(this,MenuActivity::class.java))
        }
        dataBase = DataBase(this)

        add.setOnClickListener {
            val db: SQLiteDatabase = dataBase.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DataBase.ROUTE1, name_stop.text.toString())
            contentValues.put(DataBase.ROUTE2, route.text.toString())
            contentValues.put(DataBase.MOVEMENT_TIME_TO_STOP, movement_time_to_stop.text.toString())
            contentValues.put(DataBase.NUMBER_OF_SEATS, number_of_seats.text.toString())
            contentValues.put(DataBase.TICKET_PRICE, ticket_price.text.toString())
            contentValues.put(DataBase.NUMBER_FLIGHTS_DAY, number_flights_day.text.toString())
            if (contentValues.size() != 0) {
                db.insert(DataBase.TABLE_BUS, null, contentValues)
                println("Insert add database")
            } else {
                println("000000000000000000000000")
            }
        }

        delet.setOnClickListener {
            val db: SQLiteDatabase = dataBase.writableDatabase
            db.delete(DataBase.TABLE_BUS, null, null)

        }
        dataBase.close()
    }
}
