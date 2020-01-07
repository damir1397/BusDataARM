package danzo.damir.busstationarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.ListView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import danzo.damir.busstationarm.file.Adapter
import danzo.damir.busstationarm.file.FileModel
import kotlinx.android.synthetic.main.activity_file_list.*
import java.io.*

//todo сделать окно для показа содержимого файла

class FileListActivity : AppCompatActivity() {
    private lateinit var dataMessage: ArrayList<FileModel>
    private lateinit var racketAdapter: Adapter
    private val LOG_TAG = "myLogs"
    private var FILENAME = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        val listView = findViewById<ListView>(R.id.lisr_view)
        dataMessage = ArrayList()
        racketAdapter = Adapter(this, dataMessage)


        back_list.setOnClickListener {
            finish()
            startActivity(Intent(this,MenuActivity::class.java))
        }
        listView.adapter = racketAdapter


        racketAdapter.notifyDataSetChanged()

        popupMenuHome()
        popupMenuEdit()
        readFile()
        write_file_name.setOnClickListener {
            constraint_edittext.visibility = View.GONE
            dataMessage.add(FileModel(FILENAME))
            FILENAME = file_name.text.toString()
            writeFile()
        }

    }

    fun popupMenuHome() {
        var moreMenu: Int = 0
        menuImage.setOnClickListener {
            val context: Context = ContextThemeWrapper(
                this,
                R.style.PopupMenuHome
            ) //добавление стиля для красного фона
            val popupMenu = PopupMenu(context, it)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {

                    R.id.new_file -> {
                        constraint_edittext.visibility = View.VISIBLE
                        true
                    }
                    R.id.open_file -> {
                        readFile()
                        true
                    }

                    R.id.print -> {

                        true
                    }

                    else -> false
                }
            }

            moreMenu = R.menu.menu
            popupMenu.inflate(moreMenu)
            //}

            try {
                val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldPopup.isAccessible = true
                val mPopup = fieldPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error menu icon")
            } finally {
                popupMenu.show()
            }
        }
    }

    fun popupMenuEdit() {
        var moreMenu: Int = 0
        menuEdir.setOnClickListener {
            val context: Context = ContextThemeWrapper(
                this,
                R.style.PopupMenuHome
            ) //добавление стиля для красного фона
            val popupMenu = PopupMenu(context, it)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {

                    R.id.paste -> {

                        true
                    }
                    R.id.delet -> {

                        true
                    }
                    R.id.change -> {

                        true
                    }

                    else -> false
                }
            }

            moreMenu = R.menu.edit_menu

            popupMenu.inflate(moreMenu)
            //}

            try {
                val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldPopup.isAccessible = true
                val mPopup = fieldPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error menu icon")
            } finally {
                popupMenu.show()
            }
        }
    }

    fun writeFile() {
        try { // отрываем поток для записи
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(FILENAME, Context.MODE_PRIVATE)
                )
            )
            // пишем данные
            bw.write("Содержимое файла")
            // закрываем поток
            bw.close()
            ToastUtils.showLong("Файл записан")
            Log.d(LOG_TAG, "Файл записан")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun deletfile(str: String) {
        deleteFile(str)
    }

    fun readFile() {
        try {
            // открываем поток для чтения
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(FILENAME)
                )
            )
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also({ str = it }) != null) {

                Log.d(LOG_TAG, str)
                dataMessage.add(FileModel(str.toString()))
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}
