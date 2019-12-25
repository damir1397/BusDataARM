package danzo.damir.busstationarm.file

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import danzo.damir.busstationarm.R

class Adapter(context: Context, dataMessage: ArrayList<FileModel>) :
    BaseAdapter() {
    private val dataM: ArrayList<FileModel>
    private val mContext: Context
    lateinit var dataMessage: FileModel

    init {
        dataM = dataMessage
        mContext = context
    }
    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        mListener = listener
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertview: View?, viewGroup: ViewGroup?): View? {
        val viewHolder:ViewHolder
        var rowMain = convertview
        if (rowMain == null) {
            val layoutInflater = LayoutInflater.from(mContext)
            rowMain = layoutInflater.inflate(R.layout.file_add_layout, viewGroup, false)
            // Create a new view holder instance using convert view
            viewHolder = ViewHolder(rowMain)
            rowMain.tag=viewHolder
        } else{
            viewHolder=rowMain.tag as ViewHolder
        }
        dataMessage=getItem(position)

        viewHolder.textView.text = dataMessage.filName
        return rowMain
    }

    override fun getItem(position: Int): FileModel {
        return dataM[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataM.size
    }
    private class ViewHolder(view:View){
        var textView:TextView=view.findViewById<TextView>(R.id.file_name) as TextView

    }
    companion object {

        private var mListener: AdapterView.OnItemClickListener? = null
    }
}