package com.skeleton.android.features.word

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.skeleton.android.core.dataBase.AppDatabase
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Singleton
class WordService
@Inject
constructor(contextHandler: ContextHandler, private val firebaseDatabase: FirebaseDatabase): WordApi{

    private val wordApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.wordEntityDao()

    }

    override fun words() = wordApi!!.getWords()
    override fun add(word: WordEntity) = wordApi!!.insertWord(word)

    override fun addFirebase(word: WordEntity) {

        val myRef: DatabaseReference = firebaseDatabase.getReference("word")

        myRef.push().setValue(word).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Either.Left( {
                    Log.e("INCORRECTO","No se ha podido añadir la palabra")
                })
            } else {
                Either.Left({
                    Log.e("INCORRECTO","No se ha podido añadir la palabra")
                })
            }
        }

    }

}