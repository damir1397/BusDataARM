package danzo.damir.busstationarm

import android.content.Context
import android.os.Bundle
import android.os.Environment
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


class FileListActivity : AppCompatActivity() {
    private lateinit var dataMessage: ArrayList<FileModel>
    private lateinit var racketAdapter: Adapter
    val LOG_TAG = "myLogs"
    var FILENAME = ""
    val DIR_SD = "MyFiles"
    val FILENAME_SD = "fileSD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        val listView = findViewById<ListView>(R.id.lisr_view)
        dataMessage = ArrayList<FileModel>()
        racketAdapter = Adapter(this, dataMessage)


        listView.adapter=racketAdapter


        racketAdapter.notifyDataSetChanged()

        popupMenuHome()
      //  popupMenuEdit()
        write_file_name.setOnClickListener {
            constraint_edittext.visibility=View.GONE
            dataMessage.add(FileModel(FILENAME))
            FILENAME=file_name.text.toString()
            writeFile()
        }
    }
    fun popupMenuHome() {
        var moreMenu: Int = 0
        menuImage.setOnClickListener {
            val context: Context = ContextThemeWrapper(this, R.style.PopupMenuHome) //добавление стиля для красного фона
            val popupMenu = PopupMenu(context, it)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    /*R.id.action_start_call4_doctor_activity -> {
                        startActivity(Intent(this, DetailCall4DoctorActivity::class.java))//ссылка для перехода на активити доктор
                        true
                    }*/
                    R.id.new_file -> {
                        constraint_edittext.visibility= View.VISIBLE
                        true
                    }
                    R.id.open_file -> {
                        readFile()
                        true
                    }

                    R.id.print -> {
                        readFileSD()
                        true
                    }

                    else -> false
                }
            }


                moreMenu = R.menu.menu

            // для проверки вызова если есть то всплывающее меню работает
            /* val callJson = Prefs.getString(StorageUtils.NEW_CALL_JSON, "")
             val callDeliveredTime = Prefs.getString(StorageUtils.NEW_CALL_DELIVERED_TIME, "")
             if (callJson.isNotEmpty() && callDeliveredTime.isNotEmpty()) {*/
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
            val context: Context = ContextThemeWrapper(this, R.style.PopupMenuHome) //добавление стиля для красного фона
            val popupMenu = PopupMenu(context, it)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    /*R.id.action_start_call4_doctor_activity -> {
                        startActivity(Intent(this, DetailCall4DoctorActivity::class.java))//ссылка для перехода на активити доктор
                        true
                    }*/
                    R.id.paste -> {
                        writeFileSD()
                        true
                    }
                    R.id.delet -> {
                        readFile()
                        true
                    }
                    R.id.change -> {
                        readFile()
                        true
                    }

                    else -> false
                }
            }


            moreMenu = R.menu.edit_menu

            // для проверки вызова если есть то всплывающее меню работает
            /* val callJson = Prefs.getString(StorageUtils.NEW_CALL_JSON, "")
             val callDeliveredTime = Prefs.getString(StorageUtils.NEW_CALL_DELIVERED_TIME, "")
             if (callJson.isNotEmpty() && callDeliveredTime.isNotEmpty()) {*/
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
    fun deletfile() {
        deleteFile("")
    }

    fun readFile() {
        try { // открываем поток для чтения

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

    fun writeFileSD() { // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
            )
        ) {
            ToastUtils.showLong("SD-карта не доступна: " + Environment.getExternalStorageState())
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState())
            return
        }
        // получаем путь к SD
        var sdPath: File = Environment.getExternalStorageDirectory()
        // добавляем свой каталог к пути
        sdPath = File(sdPath.getAbsolutePath().toString() + "/" + DIR_SD)
        // создаем каталог
        sdPath.mkdirs()
        // формируем объект File, который содержит путь к файлу
        val sdFile = File(sdPath, FILENAME_SD)
        try { // открываем поток для записи
            val bw = BufferedWriter(FileWriter(sdFile))

            // пишем данные
            bw.write("Содержимое файла на SD")
            // закрываем поток
            bw.close()
            ToastUtils.showLong("Файл записан на SD: " + sdFile.getAbsolutePath())
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFileSD() { // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
            )
        ) {
            ToastUtils.showLong("SD-карта не доступна: " + Environment.getExternalStorageState())
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState())
            return
        }
        // получаем путь к SD
        var sdPath: File = Environment.getExternalStorageDirectory()
        // добавляем свой каталог к пути
        sdPath = File(sdPath.getAbsolutePath().toString() + "/" + DIR_SD)
        // формируем объект File, который содержит путь к файлу
        val sdFile = File(sdPath, FILENAME_SD)
        try { // открываем поток для чтения
            val br = BufferedReader(FileReader(sdFile))
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also({ str = it }) != null) {
                Log.d(LOG_TAG, str)
                ToastUtils.showLong( str)

            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
