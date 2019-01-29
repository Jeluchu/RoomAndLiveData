package com.davidups.roomlivedata

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_word.*

class AddWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (etWord.text!!.isEmpty()){
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, "No has escrito nada de na", Toast.LENGTH_LONG).show()
            } else {
                val word = etWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.davidups.roomlivedata.worldlistsql.REPLY"
    }
}
