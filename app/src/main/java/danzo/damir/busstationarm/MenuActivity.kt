package danzo.damir.busstationarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        add_road.setOnClickListener {
            startActivity(Intent(this, AddDataActivity::class.java))
        }
        search_road.setOnClickListener {
            startActivity(Intent(this, SearchDataActivity::class.java))
        }
        write_read_file.setOnClickListener {
            startActivity(Intent(this, FileListActivity::class.java))
        }
    }
}
