package danzo.damir.busstationarm.file

import java.text.FieldPosition

interface ClicListener {
    fun mListener(position: Int)
    fun readListener(position: Int)
}