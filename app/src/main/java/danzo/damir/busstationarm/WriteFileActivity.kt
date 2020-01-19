package danzo.damir.busstationarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_write_file.*
import java.io.*

class WriteFileActivity : AppCompatActivity() {

    private val LOG_TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_file)

        val intent: String? = intent.getStringExtra("message")
        if (intent == "read") {
            readFile()
            writeButton.setOnClickListener {
                writeFile()
                ToastUtils.showLong("Файл после изменения записан")
                finish()
                startActivity(Intent(this, FileListActivity::class.java))

            }
        } else {
            writeButton.setOnClickListener {
                if (intent == "write") {
                    writeFile()
                    ToastUtils.showLong("Файл записан")
                    finish()
                    startActivity(Intent(this, FileListActivity::class.java))

                }
            }
        }

    }

    private fun writeFile() {
        val getfileName = intent.getStringExtra("filename")
        try { // отрываем поток для записи
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(getfileName, Context.MODE_PRIVATE)
                )
            )
            // пишем данные
            bw.write(writeData.text.toString())
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

    fun readFile() {
        val getfileName = intent.getStringExtra("filename")
        try {
            // открываем поток для чтения
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(getfileName)
                )
            )
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also { str = it } != null) {
                writeData.setText(str)
                Log.d(LOG_TAG, str)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
