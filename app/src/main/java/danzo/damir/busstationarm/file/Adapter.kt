package danzo.damir.busstationarm.file

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.FileUtils.deleteFile
import com.blankj.utilcode.util.ToastUtils
import danzo.damir.busstationarm.FileListActivity
import danzo.damir.busstationarm.R
import java.io.File

class Adapter(var mitems: ArrayList<FileModel>, var recyclerView: ClicListener) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    init {
        recyclerViewListener = this.recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.file_add_layout, parent, false)
        return ViewHolder(view, mitems)
    }


    override fun getItemCount(): Int {
        return mitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameWard.text = mitems[position].filName//для показа в textView

    }


    class ViewHolder(view: View, mitems: ArrayList<FileModel>) : RecyclerView.ViewHolder(view) {
        var nameWard: TextView = view.findViewById(R.id.file_name)
        var buttonDelet: ImageView = view.findViewById(R.id.deletFile)


        init {
            buttonDelet.setOnClickListener {
                recyclerViewListener.mListener(adapterPosition)
                deleteFile(mitems[adapterPosition].filName)
                ToastUtils.showLong("Файл удален")
            }
            nameWard.setOnClickListener {
                recyclerViewListener.readListener(adapterPosition)
                ToastUtils.showLong("Файл изменить ")
                stateAdapter = true
                nameFileAdapter = mitems[adapterPosition].filName
                ToastUtils.showLong("Файл изменить ="+nameFileAdapter)
            }
        }
    }

    companion object {
        lateinit var recyclerViewListener: ClicListener
        var stateAdapter: Boolean = false
        var nameFileAdapter: String = ""
    }
}


