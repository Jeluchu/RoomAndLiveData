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

        /*databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if (p0.exists()) {

                    for(h in p0.children) {
                        //val word = h.getValue(Word::class.java)
                        wordList.add(Word(p0.key.toString(), p0.value.toString()))
                        firebaseCallBack.onComplete(wordList)
                    }

                }

            }

        })*/

        val deviceListListener = object : ChildEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.d("Firebase", "onChildMoved:" + p0.key!!)
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                val aux = Word(
                    p0.key.toString(),
                    p0.child("word").value.toString()
                )

                for ( i in 0..wordList.size){
                    if(wordList[i].id == aux.id){
                        wordList[i]=aux
                        break
                    }
                }


                firebaseCallBack.onComplete(wordList)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                wordList.add(
                    Word(
                        p0.key.toString(),
                        p0.child("word").value.toString()
                    )
                )
                firebaseCallBack.onComplete(wordList)
            }

            override fun onChildRemoved(p0: DataSnapshot) {

                for (i in 0..wordList.size) {
                    if (wordList[i].id == p0.key.toString()) {
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