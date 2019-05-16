package com.skeleton.android.features.word

import android.util.Log
import com.google.firebase.database.*
import com.skeleton.android.core.custominterfaces.FirebaseCallBack
import com.skeleton.android.core.dataBase.AppDatabase
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WordService
@Inject
constructor(contextHandler: ContextHandler, private val firebaseDatabase: FirebaseDatabase): WordApi {

    private val wordApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.wordEntityDao()

    }

    var word: ArrayList<Word> = arrayListOf()
    val wordList = mutableListOf<Word>()

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun words(firebaseCallBack: FirebaseCallBack) {

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("word")


        val deviceListListener = object : ChildEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
                Log.d("Firebase", "onChildMoved:" + dataSnapshot.key!!)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {

                val response = Word(
                    dataSnapshot.key.toString(),
                    dataSnapshot.child("word").value.toString()
                )

                for ( i in 0..wordList.size){
                    if(wordList[i].id == response.id){
                        wordList[i]= response
                        break
                    }
                }


                firebaseCallBack.onComplete(wordList)
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                wordList.add(
                    Word(
                        dataSnapshot.key.toString(),
                        dataSnapshot.child("word").value.toString()
                    )
                )

                firebaseCallBack.onComplete(wordList)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

                for (i in 0..wordList.size) {
                    if (wordList[i].id == dataSnapshot.key.toString()) {
                        wordList.removeAt(i)
                        break
                    }
                }
                firebaseCallBack.onComplete(wordList)
            }
        }
        databaseReference.addChildEventListener(deviceListListener)
    }



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

}