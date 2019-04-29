package com.skeleton.android.features.word

import android.util.Log
import com.google.firebase.database.*
import com.skeleton.android.core.dataBase.AppDatabase
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordService
@Inject
constructor(contextHandler: ContextHandler, private val firebaseDatabase: FirebaseDatabase): WordApi{

    private val wordApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.wordEntityDao()

    }

    var word: ArrayList<Word> = arrayListOf()

    override fun words() = wordApi!!.getWords()
    override fun add(word: WordEntity) = wordApi!!.insertWord(word)

    override fun addFirebase(word: WordEntity) {

        val myRef: DatabaseReference = firebaseDatabase.getReference("word")

        myRef.push().setValue(word).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Either.Left { Log.e("INCORRECTO","No se ha podido añadir la palabra") }
            } else {
                Either.Left { Log.e("INCORRECTO","No se ha podido añadir la palabra") }
            }
        }

    }

    override fun readFirebaseWord(key: String) {

        val myRef: DatabaseReference = firebaseDatabase.getReference("word")

        myRef.child(key).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(Word::class.java)
                value?.let { word.add(it) }
                Either.Left { Log.e("INCORRECTO","No se ha podido añadir la palabra") }
            }
        })

    }

}