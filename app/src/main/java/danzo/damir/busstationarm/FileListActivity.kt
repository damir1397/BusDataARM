package danzo.damir.busstationarm

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putinbyte.ambulance.utils.LogUtils
import danzo.damir.busstationarm.file.Adapter
import danzo.damir.busstationarm.file.ClicListener
import danzo.damir.busstationarm.file.FileModel
import kotlinx.android.synthetic.main.activity_file_list.*
import java.io.File
import java.io.IOException

//todo сделать окно для показа содержимого файла

class FileListActivity : AppCompatActivity(), ClicListener {
    private lateinit var dataMessage: ArrayList<FileModel>
    private lateinit var adapter: Adapter
    private val LOG_TAG = javaClass.name
    private var FILENAME = ""
    private var FILEREAD = ""
    lateinit var fileName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        dataMessage = ArrayList()
        fileName = findViewById(R.id.file_name)

        //  popupMenuHome()
        val recyclerViewWard = findViewById<RecyclerView>(R.id.list_view)
        recyclerViewWard.layoutManager = LinearLayoutManager(this)

        //   /data/data/danzo.damir.busstationarm/files

        val dirName = "/data/data/danzo.damir.busstationarm/files"
        val folder = File(dirName)
        try {
            if (folder.exists()) {
                val files: Array<File>? = folder.listFiles()
                files?.forEach { file ->
                    dataMessage.add(FileModel(file.name))
                }
            }
        } catch (e: Exception) {
            LogUtils.error(LOG_TAG, e.message)
        }



        back_list.setOnClickListener {
            finish()
            startActivity(Intent(this, MenuActivity::class.java))
        }

        write_file_name.setOnClickListener {
            try {
                val getName = fileName?.text.toString()

                val intent = Intent(this, WriteFileActivity::class.java)
                intent.putExtra("message", "write")
                intent.putExtra("filename", getName)
                startActivity(intent)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        change_file.setOnClickListener {
            val getName = fileName?.text.toString()
            val intent = Intent(this, WriteFileActivity::class.java)
            intent.putExtra("message", "read")
            intent.putExtra("filename", getName)
            startActivity(intent)
        }

        adapter = Adapter(dataMessage, this)
        adapter.notifyDataSetChanged()
        recyclerViewWard.adapter = adapter


    }

    override fun mListener(position: Int) {
        itemName = dataMessage[position].filName
        println("clik==" + itemName)

    }

    override fun readListener(position: Int) {
        FILEREAD = dataMessage[position].filName
        fileName.setText(dataMessage[position].filName)

    }

    companion object {
        var itemName = ""
    }
}
