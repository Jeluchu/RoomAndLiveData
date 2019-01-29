package com.davidups.roomlivedata

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel

    companion object {
        const val addWordActivityRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Creamos y ponemos el adapter
         */
        val adapter = WordListAdapter(this)
        rvWords.adapter = adapter
        rvWords.layoutManager = LinearLayoutManager(this)

        /**
         * Asociamos el ViewModel con el activity
         */
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        /**
         * Agregamos el observador para el @LiveData devuelto por getAllWords()
         */
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        favAddWord.setOnClickListener{
            val intent = Intent(this@MainActivity, AddWordActivity::class.java)
            startActivityForResult(intent, addWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data.let {
                val word = Word(it!!.getStringExtra(AddWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(this, "No sa guardao nada de na", Toast.LENGTH_LONG).show()

        }
    }
}
