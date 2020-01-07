package danzo.damir.busstationarm.model

import com.google.gson.annotations.SerializedName
import danzo.damir.busstationarm.utils.JSONConvertable

data class DataStationarm(
    @SerializedName("nameOFStop") var nameStop: String = "",
    @SerializedName("route") var route: String = "",
    @SerializedName("movement_time_to_stop") var movement_time_to_stop:String = "",
    @SerializedName("number_of_seats") var number_of_seats: String = "",
    @SerializedName("ticket_price") var ticket_price: String = "",
    @SerializedName("number_flights_day") var number_flights_day: String = ""
) : JSONConvertable
