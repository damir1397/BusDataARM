package danzo.damir.busstationarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import danzo.damir.busstationarm.R


class Adapter(var mitems: ArrayList<ModelAdapter>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.custom_list_result, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mitems[position]
        holder.route1.text = mitems[position].route1//для показа в textView
        holder.route2.text = mitems[position].route2//для показа в textView
        holder.time.text = mitems[position].time
        holder.prise.text = mitems[position].price
        holder.seat.text = mitems[position].numberOfSeats
        holder.reysDay.text = mitems[position].numberFlightsDay
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var route1: TextView = view.findViewById(R.id.route1)
        var route2: TextView = view.findViewById(R.id.route2)
        var time: TextView = view.findViewById(R.id.time)
        var prise: TextView = view.findViewById(R.id.prise)
        var seat: TextView = view.findViewById(R.id.number_seats)
        var reysDay: TextView = view.findViewById(R.id.reis_day)

    }

}