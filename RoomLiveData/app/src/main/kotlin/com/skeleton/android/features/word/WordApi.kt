package com.skeleton.android.features.word

import com.skeleton.android.core.custominterfaces.FirebaseCallBack

internal interface WordApi {
    fun words(firebaseCallBack: FirebaseCallBack)
    fun addFirebase(word: WordEntity)
}